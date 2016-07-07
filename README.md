# Java 7 Concurrency Cookbook
## learn java concurrent
##### 1. 线程管理
* 线程的创建有两种方法 
   * 实现 Runnable 接口，使用带参数的 Thread 的构造器，创建 Thread 对象
   * 继承 Thread 对象
   * 只有start() 方法才会创建执行线程
   * 线程可以通过 setPriority() 方法设置优先级
   * Java 线程有6 种状态：new,runnable,blocked,waiting,time waiting,terminated
* 线程中断 interrupt() 和 检测 isInterrupted()
* 线程休眠 sleep() ，不会被分配 CPU 秒数
* 等待线程终止 join() ，调用它的线程将被挂起，等待线程完成
* Java 守护线程，没有其他线程运行的时候才会运行。通过 setDaemon() 方法设置，必须在 start() 方法之前调用。
* 可以通过 实现 UncaughtExceptionHandler 接口来捕获 run() 方法抛出的非运行异常，在线程中通过 setUncaughtExceptionHandler() 设置
* 线程对象捕获异常 1. 线程的异常处理器 2.线程组的 3. 默认的异常处理其
* Runnable 共享属性，线程局部变量 ThreadLocal 能够实现信息一致
* 线程组 ThreadGroup
* 线程组 不可控异常 继承 ThreadGroup ，覆盖 uncaughtException() 方法
* 工厂类创建线程 实现 ThreadFactory 接口，实现 newThread() 方法
##### 2.线程同步基础
* synchronized 关键字机制，只有执行的线程允许被访问，其他线程将被挂起，直到执行的线程完成。 synchronized 关键字会降低程序性能，只能在并发情景中需要修改共享数据的时间使用。 
* 可以使用 synchronized 修饰方法、对象，实现方法并发访问
* 修饰代码块 synchronized(Object) {  //code   }
* 线程可以在同步代码块中调用 wait() 方法，将线程休眠，使用 notify() , notifyAll() 唤醒线程
* 锁同步 Lock 比 synchronized 更灵活。
* 通过 ReentrantLock 类（实现的 Lock 接口）创建临界区，通过 lock() 方法获取对锁的控制，unlock() 释放锁 tryLock() 返回 true 获取锁，返回 false 没有获取锁
* ReadWriteLock 接口和实现类 ReentrantReadWriteLock 读取锁和写锁 lock.readLock().lock(); lock.writeLock().lock();
* 可以修改锁的公平性 例： Lock lock=new ReentrantLock(true);
* 通过 Condition 接口声明允许线程获取锁并查看等待某一个条件是否满足，通过Lock 接口声明的 newCondition() 方法创建。
* Condition await() ,signal() ,signalAll() 必须在 lock() 和 unlock() 方法之间
##### 3.线程同步辅助类
* 信号量 Semaphore 机制，保护共享资源的访问。 acquire() ,release() 获取释放，可以修改公平性
* CountDownLatch 用来同步执行多个任务的一个或多个线程。 当计数器到达 0 将不起作用。 基本元素 1.定义先行完成的操作数目 2. await() 等待完成线程调用 3. countDown() 方法，每个被等待的事件在完成的时候调用
* CyclicBarrier 允许多个线程（ await() ）在某个点上进行同步。然后可以使用 Runnable 对象作为线程执行
* Phaser 允许执行并发多阶段任务
* Exchanger 并发任务之间交换数据
##### 4.线程执行器
* ThreadPoolExecutor 通过 Executors 工厂类创建对象 通过submit() 启动
* 当线程数量是合理的并且线程只会执行很短的时间适合使用 newCachedThreadPool() 方法创建执行器。[如果执行新任务，缓存线程池创建新线程，如果执行完线程可用，则线程池将会重用这些线程，减少了创建线程所花费的时间]
* newFixedThreadPool() 固定大小的执行器
* 执行器通过 Callable 接口返回结果 和 Future 接口获取结果
* 可以通过 inVokeAny() 获取第一个结果 
* invokeAll() 等待所有方法完成
* ScheduledThreadPool 能够延迟执行或周期性执行，通过 schedule() 方法启动 shceduledAtFixedRate() 周期性
* 可以通过 Future 接口的 cancel() 方法取消已经发送给执行器的任务
* FutureTask 提供 done() 方法允许执行器任务执行结束后执行一些代码
* 在一个对象里发送任务给执行器，在另一个对象里处理结果可以使用 CompletionService 类
* 使用 shutdown() 后，发送任务会被拒绝。处理在执行器中被拒绝的任务使用 RejectedExecutionHandler 接口。
##### 5. Fork/Join 框架
* Fork/Join 框架能通过分治，将问题拆分为小问题，使用 工作窃取算法(Work-Stealing Algorithm) 。使用 Join 操作使主任务等待子任务完成
* 执行这些任务的线程为工作者线程，工作者线程寻找其他仍未被执行的任务，然后执行。
* 任务只能使用 fork() 和 join() 操作当作同步机制，如果使用其他同步机制，工作者线程就不能执行其他任务
* 任务不能执行 I/O 操作
* 任务不能抛出 checked exception
* ForkJoinPool 实现了 ExecutorService 接口和工作窃取算法
* ForkJoinTask 是在 ForkJoinPool 中执行的基类
* 为了实现 Fork/Join 任务需要实现 RecursiveAction (没有返回结果) 或 RecursiveTask(有返回结果)，他们父类实现了 Serializable 接口，需要声明类的 serialVersionUID 属性 =1L
* ForkJoinPool 在同步情况下允许采用工作做窃取算法。当采用异步方法时，无法采用 工作窃取算法来提高性能。fork() 方法发送任务到线程池，会立刻返回。使用 join() 等待任务返回，并且不能被中断，被中断有 InterruptedException 异常，如果采用 get() 方法，任何异常都会抛出 ExecutionException 异常。
* 可以通过 isCompletedAbnormally() 检查主任务或者子任务是否抛出了异常，并进行处理。
* 可以通过辅助类 实现任务撤销 cancel
##### 6. 并发集合
* 阻塞式集合 包括添加方法和移除数据方法，如果为空或为满将阻塞到可以执行
* 非阻塞集合 包括添加方法和移除数据方法，将返回 null 或抛出异常，将不会被阻塞
* ConcurrentLinkedDeque 非阻塞式列表
* LinkedBlockDeque 阻塞式列表
* LinkedTransferQueue 用于数据生成或消费的阻塞式列表
* PriorityBlockingQueue 优先级的阻塞，泛型对象需要实现 Comparable 对象
* DelayQueue 延迟 泛型类型需要继承Delayed接口实现 getDelay() 返回激活日期剩余时间，和 compareTo(Delayed o) 比较延迟值大小 
* ConcurrentSkipListMap 非阻塞式课遍历映射 效率和二叉树相近，映射对象： ConcurrentNavigableMap。
* ThreadLocalRandom 随机数字，线程本地变量，具有更好性能
* AtomicLong AtomicReference 原子类型 ，对象，所有操作基于 CAS(Compare and Set)，如果跟原来的值相同，则新值覆盖旧值，否则操作重做
##### 7. 定制并发类
* 定制 ThreadPoolExecutor
* 实现基于优先级的 Executor 类
* 实现 ThreadFactory 接口生成定制线程，并且可以在 Executor 对象中使用
* 定时线程池任务 通过继承 FutureTask ，实现 RunnableScheduledFuture 接口实现，通过 ScheduledThreadPoolExecutor 调用
* Fork/Join 框架有两个元素 ： 1.任务队列，存放等待被执行的任务。 2. 执行这些任务的线程池。 通过继承 ForkJoinWorkerThread 重写工作者线程，进行操作
* 通过继承 AbstractQueuedSynchronizer 实现带有锁或信号特性的同步机制，提供对临界区代码的控制访问，并对等待访问临界区代码的阻塞线程进行管理。继承 Lock 接口实现实现锁。
* 通过实现 TransferQueue 接口实现传输队列，通过继承 PriorityBlockingQueueu实现优先级访问， 实现基于优先级的传输队列。
* 通过继承原子对象来实现自己的原子对象
##### 8.测试并发程序
* 通过继承 ReentrantLock 类实现对内部访问权限的修改，实现对 Lock 接口的监控
* 监控 Phaser 类，将对象状态输出
* 监控执行器框架 ThreadPoolExecutor ，对状态进行输出
* 监控 Fork/Join 池，对 Fork/Join 线程进行监控
* 使用 Logger 输出日志
* 使用 FindBugs 分析并发代码
* 使用 MultithreadedTC(测试并发程序的Java 类库) 测试并发代码
# 总结
* 虽然看完一本书，但是什么都不记得！
* 上面的文章大概就是对类库的罗列，具体使用的时候需要查看 API
* 代码有 Demo 可以参考
* 大概就是这个样子，好渣
