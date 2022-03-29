import random


def ciao_word(str):
    return "".join(sorted(str))


def add_ciao_dict(ciao_dict, word):
    c_word = ciao_word(word)
    if c_word in ciao_dict:
        ciao_dict[c_word].add(word)
    else:
        ciao_dict.update({c_word: {word}})


def create_dict(path):
    ciao_dict = {}
    with open(path, 'r') as f:
        for line in f:
            add_ciao_dict(ciao_dict, line.rstrip('\n'))
    return ciao_dict


def replace_anagrams(dict, line):
    result = ""
    list = line.split(" ")
    for word in list:
        c_word = ciao_word(word)
        if c_word in dict:
            word_set = dict[c_word]
            if len(word_set) == 1:
                result = result + " " + word
            else:
                new_set = word_set.copy()
                new_set.remove(word)
                result = result + " " + random.choice(tuple(new_set))
        else:
            result = result + " " + word
    return result


if __name__ == "__main__":
    ciao_dict = create_dict("anagram.txt")
    print(replace_anagrams(ciao_dict, "hello race late list"))
