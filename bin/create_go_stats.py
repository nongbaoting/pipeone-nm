import os, time
if os.path.isfile('go_forStats.txt') == False:
    print(time.asctime(time.localtime(time.time())) + ' Create go_forStats:')
    f1 = open('go_annotations.txt', 'r')
    f2 = open('go_forStats.txt', 'w')
    for i in f1.readlines():
        j = i.split('\t')
        for k in j[1].split(','):
            m = j[0] + '\t' + k
            if (m[-1] != '\n'):
                m = m + '\n'
            print(m)
            f2.write(m)
    f1.close()
    f2.close()