import os, time
if os.path.isfile('Trinotate_lncRNA.txt') == False:
    print(time.asctime(time.localtime(time.time())) + ' Create TrinotateRNAs:')
    mRNA_list = []
    tn = open('Trinotate.xls')
    rRNA = open('Trinotate_rRNA.txt', 'w')
    lncRNA = open('Trinotate_lncRNA.txt', 'w')
    tn.readline()
    for line in tn.readlines():
        unit = line.split('\t')
        if unit[3] != '.':
            rRNA.write(unit[1] + '\n')
        elif unit[2] == '.' and unit[16] == '.\n':
            lncRNA.write(unit[1] + '\n')
        else:
            mRNA_list.append(unit[1])
    tn.close()
    rRNA.close()
    lncRNA.close()
    mRNA_list = list(set(mRNA_list))
    mRNA = open('Trinotate_mRNA.txt', 'w')
    for G in mRNA_list: mRNA.write(G + '\n')
    mRNA.close()