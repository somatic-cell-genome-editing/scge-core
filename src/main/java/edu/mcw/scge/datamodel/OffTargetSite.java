package edu.mcw.scge.datamodel;


public class OffTargetSite {

    private long guideId;
    private String offtargetSequence;
    private String chromosome;
    private String start;
    private String stop;
    private String strand;
    private int mismatches;
    private String target;
    private String seqType;
    private int noOfReads;

    public long getGuideId() {
        return guideId;
    }

    public void setGuideId(long guideId) {
        this.guideId = guideId;
    }

    public String getOfftargetSequence() {
        return offtargetSequence;
    }

    public void setOfftargetSequence(String offtargetSequence) {
        this.offtargetSequence = offtargetSequence;
    }

    public String getChromosome() {
        return chromosome;
    }

    public void setChromosome(String chromosome) {
        this.chromosome = chromosome;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getStop() {
        return stop;
    }

    public void setStop(String stop) {
        this.stop = stop;
    }

    public String getStrand() {
        return strand;
    }

    public void setStrand(String strand) {
        this.strand = strand;
    }

    public int getMismatches() {
        return mismatches;
    }

    public void setMismatches(int mismatches) {
        this.mismatches = mismatches;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getSeqType() {
        return seqType;
    }

    public void setSeqType(String seqType) {
        this.seqType = seqType;
    }

    public int getNoOfReads() {
        return noOfReads;
    }

    public void setNoOfReads(int noOfReads) {
        this.noOfReads = noOfReads;
    }
}
