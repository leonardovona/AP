# Leonardo Vona 545042
#
# Advanced Programming Assignment 2
#
# Exercises 5 - 6

import functools
from time import perf_counter, sleep
from statistics import mean, variance
from threading import Thread


# Parametric decorator. A function f decorated by bench is executed in
# parallel by n_threads. Each threads executes sequentially f seq_iter times.
# The whole process is executed iter times. Each time the external iteration
# is repeated, the execution time is measured.
# The decorator returns a dictionary containing the name of the function, the
# tuple of arguments, the values of n_threads, seq_iter and iter, as well as
# the mean and the variance of the execution times of the iter iterations.
def bench(n_threads=1, seq_iter=1, iter=1):
    def internal_bench_dec(f):
        @functools.wraps(f)
        def wrapper_bench(*args, **kwargs):
            # Function wrapper for internal thread iteration of the decorated
            # function executions
            def function_wrapper():
                for _ in range(iter):
                    f(*args, **kwargs)

            completion_time = []

            for _ in range(iter):
                start = perf_counter()
                threads = []

                for _ in range(n_threads):
                    t = Thread(target=function_wrapper)
                    threads.append(t)
                    t.start()

                for thread in threads:
                    thread.join()

                completion_time.append(perf_counter() - start)

            # The mean of a single execution is the single completion time and
            # the variance is zero
            if iter == 1:
                completion_mean = completion_time[0]
                completion_variance = 0
            else:
                completion_mean = mean(completion_time)
                completion_variance = variance(completion_time)

            return {'fun': f.__name__, 'args': args + (kwargs, ),
                    'n_threads': n_threads, 'seq_iter': seq_iter, 'iter': iter,
                    'mean': completion_mean, 'variance': completion_variance}
        return wrapper_bench
    return internal_bench_dec


# Executes in parallel function fun on arguments args.
# iter is the iter parameter of the bench decorator.
# The execution is repeated varying the iterations and parallelism degree:
#   - 16 times on 1 thread
#   - 8 times on 2 threads
#   - 4 times on 4 threads
#   - 2 times on 8 threads
# Each execution result is written to a properly named file.
def test(iter, fun, args):
    for i in [1, 2, 4, 8]:
        with open(f'{fun.__name__}_{args}_{i}_{16//i}', 'w') as f:
            f.write(str(bench(i, 16//i, iter)(fun)(*args)))


def just_wait(n):  # NOOP for n/10 seconds
    sleep(n * 0.1)


def grezzo(n):  # CPU intensive
    for i in range(2**n):
        pass


# Runs tests with different degrees of CPU usage
if __name__ == "__main__":
    test(5, just_wait, (.5,))

    test(10, grezzo, (2,))
    test(10, grezzo, (4,))
    test(10, grezzo, (8,))
    test(10, grezzo, (16,))

#   The tests show that for the 'just_wait' function, the more the threads are,
# the higher is the speedup. In particular, the speedup is linear because when
# the number of threads doubles, the completion time halves. This is due to the
# fact that time.sleep function releases the resources allocated to the thread
# and then allows the scheduler to schedule other threads, resulting in a
# reduction of the completion time with respect to multiple sequential
# executions of the same function.
#
#   For what concerns the 'grezzo' function the results differs depending on
# the parameter 'n':
#   -   if n is small (< 18 with 10 seq_iter on the machine where the tests
#     have been run), the overhead caused by the creation and scheduling of
#     the threads is not neglectible with respect to the time to compute the
#     function.
#   -   if n is large, the overhead is neglectible compared to the time needed
#     to compute the function, but still the Global Interpreter Lock allows
#     the execution of a single thread at a time; then, varying the number of
#     threads does not change the overall completion time.
#  In both cases, the statement "Two threads calling a function may
# take twice as much time as a single thread calling the function twice"
# is true for the 'grezzo' function.
#
#   Putting all together, from the results of the tests we can say that on the
# machine where the tests were run, the statement is true in general.
