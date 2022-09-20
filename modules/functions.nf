#!/usr/bin/env nextflow

nextflow.enable.dsl=2

// make a result directory
def mkdir_tmp(tmpdir){
    def myTmpDir = file(tmpdir)
    def result = myTmpDir.mkdir()
    myTmpDir.setPermissions(7,7,7)
}

// get files from a directory
def get_dir_files(mydir, par_name){
    if(mydir){
        myfiles = Channel
			.fromPath("${mydir }/*")
			.ifEmpty { exit 1, " ${mydir}  not found !" }
        return myfiles
    }else{
         exit 1, "${par_name} value is null !"
    }
    
}

// get (base, index)
def get_base_index( par_idx ){
    indices = Channel
        .fromPath("${par_idx}*/")
        .ifEmpty { exit 1, "hisat2 or bowtie index not found: ${par_idx}" }
        .collect()
    idx_base= par_idx.toString().split('/')[-1]
    return [idx_base, indices ]
}

// check if the file exists
def check_file(myfile, par_name){ 
    if ( myfile ){
        myfile_ = file(myfile)
        if( ! myfile_.exists()  ) exit 1, "File: ${myfile} does not exist!"
    }else{ exit 1, "${par_name} value is null !"}
    return myfile_
}

// get srafiles
def input_sra(sra){
    ext = sra.split('\\*')[-1]
    sraFiles = Channel
        .fromPath(sra)
        .ifEmpty{exit 1, "Can not find any files matching: ${sra}"}
        .map{file ->
            def  key = file.getName().replace(ext,'')
            return tuple(key, tuple(file)  )
            }
    return sraFiles

}
//get fna files
def input_fna(fna){
    fnaFiles = Channel
        .fromPath(fna)
        .ifEmpty{exit 1, "Can not find any files matching: ${fna}"}
    return fnaFiles
}
// // return tuple(key, tuple(file)) from reads (single or double end)
def input_reads(reads, singleEnd){
    if( ! singleEnd ){
        ch_reads = Channel
            .fromFilePairs(reads)
            .ifEmpty{  exit 1, "Can not find any file matching: ${reads}"}
            
    }else{
        ext = reads.split('\\*')[-1]
        ch_reads = Channel
            .fromPath(reads)
            .ifEmpty{exit 1, "Can not find any reads matching: ${reads}"}
            .map{file ->
                def  key = file.getName().replace(ext,'')
                return tuple(key, tuple(file)  )
                }
        
    }
    return ch_reads
}

// pipeone logo
params.monochrome_logs = false
def Header() {
    // Log colors ANSI codes
    // linux command: figlet PipeOne
    c_reset = params.monochrome_logs ? '' : "\033[0m";
    c_dim = params.monochrome_logs ? '' : "\033[2m";
    c_black = params.monochrome_logs ? '' : "\033[0;30m";
    c_green = params.monochrome_logs ? '' : "\033[0;32m";
    c_yellow = params.monochrome_logs ? '' : "\033[0;33m";
    c_blue = params.monochrome_logs ? '' : "\033[0;34m";
    c_purple = params.monochrome_logs ? '' : "\033[0;35m";
    c_cyan = params.monochrome_logs ? '' : "\033[0;36m";
    c_white = params.monochrome_logs ? '' : "\033[0;37m";

    return """-${c_yellow}----------------------------------------------------------------------------${c_reset}-


            ${c_yellow} ____  _             ___                   _   _ __  __  ${c_reset}
            ${c_yellow}|  _ \\(_)_ __   ___ / _ \\ _ __   ___      | \\ | |  \\/  | ${c_reset}
            ${c_yellow}| |_) | | '_ \\ / _ \\ | | | '_ \\ / _ \\_____|  \\| | |\\/| | ${c_reset}
            ${c_yellow}|  __/| | |_) |  __/ |_| | | | |  __/_____| |\\  | |  | | ${c_reset}
            ${c_yellow}|_|   |_| .__/ \\___|\\___/|_| |_|\\___|     |_| \\_|_|  |_| ${c_reset}
            ${c_yellow}        |_| ${c_reset}


            ${c_purple}${workflow.manifest.name} v${workflow.manifest.version}${c_reset}
            ${c_purple}Author: ${workflow.manifest.author}${c_reset}
-${c_yellow}------------------------------------------------------------------------------${c_reset}-
    """.stripIndent()
}

