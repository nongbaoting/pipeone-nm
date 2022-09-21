# Prerequisites

1. [Docker](https://www.docker.com/) or [conda](https://docs.conda.io/en/latest/miniconda.html)
2. Java (version >= 1.7)
3. [Nextflow](https://www.nextflow.io/) (version >= 20.07.1.5413)

# Installation

### __1. Download PipeOne-NM__

```
https://github.com/nongbaoting/pipeone-nm.git
```

### __2. Setup__

##### 2.1 pull docker image or install conda environments

**Option 1: pull docker images run by dock**

```bash
docker push nongbaoting/pipeone_nm:latest
```

**Option 2: install conda environments** 

```
bash ./INSTALL/install.sh
```

##### __2.2 softwares need to be registeration__

* Trinotate
* tmhmm v2 (free academic download) [http://www.cbs.dtu.dk/cgi-bin/nph-sw_request?tmhmm](http://www.cbs.dtu.dk/cgi-bin/nph-sw_request?tmhmm)

* RNAMMER (free academic download) [http://www.cbs.dtu.dk/cgi-bin/sw_request?rnammer](http://www.cbs.dtu.dk/cgi-bin/sw_request?rnammer)
* signalP v4 (free academic download) [http://www.cbs.dtu.dk/cgi-bin/nph-sw_request?signalp](http://www.cbs.dtu.dk/cgi-bin/nph-sw_request?signalp)


### __3. Configuration__

Modify the program configuration file `conf/genomes.config`,  change the line below:

```
params {
  genomes {
  "fish_test" {
	refgenome = "/path/to/genome/genomic.fna"
	ref_miRNA = '/path/to/genome/public5/l/FishmiRNA-June2021-dre-mature.fasta'			  // for miranda
	uniprot = "/path/to/genome/uniprot_sprot.pep"                                                     // for Trinotate
	pfam = "/path/to/genome/Pfam-A.hmm"                                                               // for Trinotate
	sqlite = "/path/to/genome/Trinotate_20210616.sqlite"                                         	  // for Trinotate 
	min_length = 150
    }
  }
}
```

reqiured files which need for Trinotate, you could find this in [Trinotate ](https://github.com/Trinotate/Trinotate.github.io/blob/master/index.asciidoc#SequencesRequired)installatio instruction 

* refgenome
* ref_miRNA
* uniprot
* pfam
* sqlite

# __Run the Pipeline__


```
nextflow run /path/to/pipeOne-nm  -profile docker --sra './sra/*.sra' --genome fish_test
```

* Download RNA-seq data in sra format for test from NCBI
* Required Parameters
  * RNA-seq data
    * --sra, RNA-seq data in sra format
    * or --fastq, RNA-seq data in fastq format
  * --genome, genome version defined in `conf/genomes.config`,
