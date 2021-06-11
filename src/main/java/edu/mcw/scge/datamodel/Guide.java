package edu.mcw.scge.datamodel;

public class Guide  {
    private long guide_id;

    private String species;
    private String targetLocus;
    private String targetSequence;
    private String pam;
    private String assembly;
    private String chr;
    private String start;
    private String stop;
    private String strand;
    private String grnaLabId;
    private String spacerLength;
    private String spacerSequence;
    private String repeatSequence;
    private String guide;
    private String forwardPrimer;
    private String reversePrimer;
    private String linkerSequence;
    private String antiRepeatSequence;
    private String stemloop1Sequence;
    private String stemloop2Sequence;
    private String stemloop3Sequence;
    private String source;
    private String guideFormat;
    private String modifications;
    private String guideDescription;
    private String standardScaffoldSequence;
    private int tier;
    private String ivtConstructSource;
    private String vectorId;
    private String vectorName;
    private String vectorDescription;
    private String vectorType;
    private String annotatedMap;
    private String specificityRatio;

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    public String getSpacerSequence() {
        return spacerSequence;
    }

    public void setSpacerSequence(String spacerSequence) {
        this.spacerSequence = spacerSequence;
    }

    public long getGuide_id() {
        return guide_id;
    }

    public void setGuide_id(long guide_id) {
        this.guide_id = guide_id;
    }

    public String getGuide() {
        return guide;
    }

    public void setGuide(String guide) {
        this.guide = guide;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getTargetLocus() {
        return targetLocus;
    }

    public void setTargetLocus(String targetLocus) {
        this.targetLocus = targetLocus;
    }

    public String getTargetSequence() {
        return targetSequence;
    }

    public void setTargetSequence(String targetSequence) {
        this.targetSequence = targetSequence;
    }

    public String getPam() {
        return pam;
    }

    public void setPam(String pam) {
        this.pam = pam;
    }

    public String getAssembly() {
        return assembly;
    }

    public void setAssembly(String assembly) {
        this.assembly = assembly;
    }

    public String getChr() {
        return chr;
    }

    public void setChr(String chr) {
        this.chr = chr;
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

    public String getGrnaLabId() {
        return grnaLabId;
    }

    public void setGrnaLabId(String grnaLabId) {
        this.grnaLabId = grnaLabId;
    }

    public String getSpacerLength() {
        return spacerLength;
    }

    public void setSpacerLength(String spacerLength) {
        this.spacerLength = spacerLength;
    }

    public String getRepeatSequence() {
        return repeatSequence;
    }

    public void setRepeatSequence(String repeatSequence) {
        this.repeatSequence = repeatSequence;
    }

    public String getForwardPrimer() {
        return forwardPrimer;
    }

    public void setForwardPrimer(String forwardPrimer) {
        this.forwardPrimer = forwardPrimer;
    }

    public String getReversePrimer() {
        return reversePrimer;
    }

    public void setReversePrimer(String reversePrimer) {
        this.reversePrimer = reversePrimer;
    }

    public String getLinkerSequence() {
        return linkerSequence;
    }

    public void setLinkerSequence(String linkerSequence) {
        this.linkerSequence = linkerSequence;
    }

    public String getAntiRepeatSequence() {
        return antiRepeatSequence;
    }

    public void setAntiRepeatSequence(String antiRepeatSequence) {
        this.antiRepeatSequence = antiRepeatSequence;
    }

    public String getStemloop1Sequence() {
        return stemloop1Sequence;
    }

    public void setStemloop1Sequence(String stemloop1Sequence) {
        this.stemloop1Sequence = stemloop1Sequence;
    }

    public String getStemloop2Sequence() {
        return stemloop2Sequence;
    }

    public void setStemloop2Sequence(String stemloop2Sequence) {
        this.stemloop2Sequence = stemloop2Sequence;
    }

    public String getStemloop3Sequence() {
        return stemloop3Sequence;
    }

    public void setStemloop3Sequence(String stemloop3Sequence) {
        this.stemloop3Sequence = stemloop3Sequence;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getGuideFormat() {
        return guideFormat;
    }

    public void setGuideFormat(String guideFormat) {
        this.guideFormat = guideFormat;
    }

    public String getModifications() {
        return modifications;
    }

    public void setModifications(String modifications) {
        this.modifications = modifications;
    }

    public String getGuideDescription() {
        return guideDescription;
    }

    public void setGuideDescription(String guideDescription) {
        this.guideDescription = guideDescription;
    }

    public String getStandardScaffoldSequence() {
        return standardScaffoldSequence;
    }

    public void setStandardScaffoldSequence(String standardScaffoldSequence) {
        this.standardScaffoldSequence = standardScaffoldSequence;
    }

    public String getIvtConstructSource() {
        return ivtConstructSource;
    }

    public void setIvtConstructSource(String ivtConstructSource) {
        this.ivtConstructSource = ivtConstructSource;
    }

    public String getVectorId() {
        return vectorId;
    }

    public void setVectorId(String vectorId) {
        this.vectorId = vectorId;
    }

    public String getVectorName() {
        return vectorName;
    }

    public void setVectorName(String vectorName) {
        this.vectorName = vectorName;
    }

    public String getVectorDescription() {
        return vectorDescription;
    }

    public void setVectorDescription(String vectorDescription) {
        this.vectorDescription = vectorDescription;
    }

    public String getVectorType() {
        return vectorType;
    }

    public void setVectorType(String vectorType) {
        this.vectorType = vectorType;
    }

    public String getAnnotatedMap() {
        return annotatedMap;
    }

    public void setAnnotatedMap(String annotatedMap) {
        this.annotatedMap = annotatedMap;
    }

    public String getSpecificityRatio() {
        return specificityRatio;
    }

    public void setSpecificityRatio(String specificityRatio) {
        this.specificityRatio = specificityRatio;
    }
}
