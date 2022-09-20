import os, time
if os.path.isfile('Transcripts.fasta.gene_trans_map') == False:
    print(time.asctime(time.localtime(time.time())) + ' Create gene-trans map:')
    fa = open('Transcripts.fasta')
    gtm = open('Transcripts.fasta.gene_trans_map', 'w')
    for line in fa.readlines():
        if line[0] == '>':
            line = line.lstrip('>')
            line = line.rstrip('\n')
            line = line.split('\t')
            tran = line[0]
            gene = line[1]
            gtm.write(gene + '\t' + tran + '\n')
else:
    print('Gene-trans map already extracted!')
os.chdir('..')