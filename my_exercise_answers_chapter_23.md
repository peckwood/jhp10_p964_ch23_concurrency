#### 23.5

a. T

b.

c. F, deadlock occurs when a method waits for another to finish, while in the meantime the other one is waiting for this one to finish.

d. T

#### 23.6

#### 23.7 

#### 23.8

- issues I/O request -> I/O completes/interrupt
- enter `synchronized` statement -> acquires lock/interrupt


#### 23.9

- If we don't places calls to Lock method `unlock` in a `finally` block. When an exception is thrown, unlock is not called and deadlock would occur.
- Using a ReentrantLock with a fairness policy prevents infinity postponement because as a thread waits, its priority will get higher and will event be executed. This avoides infinity postponement from lower priority thread waiting for higher priority thread indefinitely.