package edu.mcw.scge.datamodel;

/**
 * Created by jdepons on 9/10/2019.
 */
public class Editor {
    private long id;
    private String type;
    private String subType;
    private String symbol;
    private String alias;
    private String species;
    private String editorVariant;
    private String pamPreference;
    private String substrateTarget;
    private String activity;
    private String fusion;
    private String dsbCleavageType;
    private String target_sequence;
    private String source;
    private String proteinSequence;
    private String editorDescription;
    private String annotatedMap;
    private String targetLocus;
    private String assembly;
    private String chr;
    private String start;
    private String stop;
    private String strand;
    private String orientation;

    private int tier;

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSubType() {
        return subType;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getPamPreference() {
        return pamPreference;
    }

    public void setPamPreference(String pamPreference) {
        this.pamPreference = pamPreference;
    }

    public String getEditorVariant() {
        return editorVariant;
    }

    public void setEditorVariant(String editorVariant) {
        this.editorVariant = editorVariant;
    }

    public String getFusion() {
        return fusion;
    }

    public void setFusion(String fusion) {
        this.fusion = fusion;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getDsbCleavageType() {
        return dsbCleavageType;
    }

    public void setDsbCleavageType(String dsbCleavageType) {
        this.dsbCleavageType = dsbCleavageType;
    }

    public String getTarget_sequence() {
        return target_sequence;
    }

    public void setTarget_sequence(String target_sequence) {
        this.target_sequence = target_sequence;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getSubstrateTarget() {
        return substrateTarget;
    }

    public void setSubstrateTarget(String substrateTarget) {
        this.substrateTarget = substrateTarget;
    }

    public String getProteinSequence() {
        return proteinSequence;
    }

    public void setProteinSequence(String proteinSequence) {
        this.proteinSequence = proteinSequence;
    }

    public String getEditorDescription() {
        return editorDescription;
    }

    public void setEditorDescription(String editorDescription) {
        this.editorDescription = editorDescription;
    }

    public String getAnnotatedMap() {
        return annotatedMap;
    }

    public void setAnnotatedMap(String annotatedMap) {
        this.annotatedMap = annotatedMap;
    }

    public String getTargetLocus() {
        return targetLocus;
    }

    public void setTargetLocus(String targetLocus) {
        this.targetLocus = targetLocus;
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

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }
}
