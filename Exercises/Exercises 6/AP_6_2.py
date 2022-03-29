import functools


def block_ten_dec(g1):
    @functools.wraps(g1)
    def wrapper_block_ten_dec(*args, **kwargs):
        li = []
        for i in g1(*args, **kwargs):
            li.append(i)
            if len(li) == 10:
                yield li
                li = []
        if li:
            yield li
    return wrapper_block_ten_dec


def block_dec(block_size):
    def internal_block_dec(g1):
        @functools.wraps(g1)
        def wrapper_block_dec(*args, **kwargs):
            li = []
            for i in g1(*args, **kwargs):
                li.append(i)
                if len(li) == block_size:
                    yield li
                    li = []
            if li:
                yield li
        return wrapper_block_dec
    return internal_block_dec


@block_dec(block_size=5)
def fibonacci(n):
    a, b = 0, 1
    for _ in range(0, n):
        yield a
        a, b = b, a + b


if __name__ == "__main__":
    for elem in fibonacci(100):
        print(elem)
