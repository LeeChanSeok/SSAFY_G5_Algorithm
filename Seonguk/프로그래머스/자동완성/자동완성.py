def make_trie(trie, words):
    for word in words:
        cur=trie
        l=len(word)
        for w in word:
            if w in cur:
                cur=cur[w]
                cur['!'].append(l)
            else:
                cur[w]={}
                cur = cur[w]
                cur['!']=[l]

def search_trie(trie, word):
    if len(trie[word[0]]['!']) == 1:
        return 1

    cnt=0
    for w in word:
        if w in trie:
            trie=trie[w]
            cnt+=1
        if len(trie['!']) == 1:
            return cnt
        
    return len(word)
        
def solution(words):
    answer = 0
    trie={}
    make_trie(trie, words)
    for word in words:
        answer+=search_trie(trie, word)
    
    return answer
