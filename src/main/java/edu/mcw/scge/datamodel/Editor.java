package edu.mcw.scge.datamodel;

/**
 * Created by jdepons on 9/10/2019.
 */
public class Editor {
    private int id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
}
