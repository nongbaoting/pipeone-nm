
process Transdecoder_LongOrfs {
		
        publishDir "${params.outdir}/transdecoder/", mode: 'link',
            saveAs: {filename -> 
                if (filename =~/log/) "logs/${filename}"
                else if(filename =~ /dir/) "$filename"
                else null
                }
        
        input:
        path("Transcripts.fasta")
        
        
        
        output:
        path("TransDecoder.LongOrfs.log"), emit: log
        path("Transcripts.fasta.transdecoder_dir/*"), emit: dir
        
        
        
        script:

        """
        set +u; source activate pipeone_nm; set -u
        TransDecoder.LongOrfs -t Transcripts.fasta > TransDecoder.LongOrfs.log 2>&1

        
        """
}

process Blastp_LongOrfs {
	label "bigCPU"
        publishDir "${params.outdir}/transdecoder/", mode: 'link',
            saveAs: {filename -> 
                if (filename =~/blastp/) "blastp/${filename}"
                else if (filename =~/log/) "logs/${filename}"
                else null
                }
        
        input:
        path("Transcripts.fasta.transdecoder_dir/*")
        path("uniprot_sprot.pep")
        
        
        output:
        path("blastp_LongOrfs.outfmt6"), emit: blastp_LongOrfs
        path("makeblastdb.log")
        path("blast_uniprot_sprot.*"), emit: makeblastu
        
        
        script:

        """
        set +u; source activate pipeone_nm; set -u
        makeblastdb -in uniprot_sprot.pep -dbtype prot -out blast_uniprot_sprot > makeblastdb.log

        blastp -query Transcripts.fasta.transdecoder_dir/longest_orfs.pep \\
        -db blast_uniprot_sprot -out blastp_LongOrfs.outfmt6 \\
        -max_target_seqs 1 -outfmt 6 -evalue 1e-5 -num_threads  ${task.cpus }

        
        """
}

process Hmmpress {
        publishDir "${params.outdir}/Hmmpress", mode: 'copy'

        input:
        path("Pfam-A.hmm")

        output:
        path ("Pfam-A.hmm.*"),  emit: press
        path("hmmpress.log")

        script:
        """
        set +u; source activate pipeone_nm; set -u
        hmmpress Pfam-A.hmm > hmmpress.log
        """

}

process Hmmscan_LongOrfs {
	label "bigCPU"
        publishDir "${params.outdir}/transdecoder/", mode: 'link',
            saveAs: {filename -> 
                if (filename =~/domtblout/) "domtblout/${filename}"
                else if (filename =~/log/) "logs/${filename}"
                else null
                }
        
        input:
        path("Transcripts.fasta.transdecoder_dir/*")
        path("*")
        
        
        output:
        path("pfam.domtblout"), emit: domtblout
       
      
        
        script:

        """
        set +u; source activate pipeone_nm; set -u
        hmmscan --cpu ${task.cpus} --domtblout pfam.domtblout Pfam-A.hmm \\
        Transcripts.fasta.transdecoder_dir/longest_orfs.pep > /dev/null
        
        """
}

process Transdecoder_Predict {
		
        publishDir "${params.outdir}/transdecoder/", mode: 'link',
            saveAs: {filename -> 
                if (filename =~/pep/) "predict/${filename}"
                else if (filename =~/log/) "logs/${filename}"
                else null
                }
        
        input:
        path("Transcripts.fasta")
        path("pfam.domtblout")
        path("blastp.outfmt6")
        path("Transcripts.fasta.transdecoder_dir/*")
        
        
        output:
        path("Transcripts.fasta.transdecoder.pep"), emit: predict
        path("TransDecoder.Predict.log")
        
        
        script:

        """
        set +u; source activate pipeone_nm; set -u
        TransDecoder.Predict -t Transcripts.fasta --retain_pfam_hits pfam.domtblout \\
        --retain_blastp_hits blastp.outfmt6 1> TransDecoder.Predict.log 2>&1

        
        """
}