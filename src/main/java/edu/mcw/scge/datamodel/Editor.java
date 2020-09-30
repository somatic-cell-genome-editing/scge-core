package edu.mcw.scge.datamodel;

/**
 * Created by jdepons on 9/10/2019.
 */
public class Editor {
    private int id;
    private String subType;
    private String type;
    private String species;
    private String pamPreference;
    private String editorVariant;
    private String fusion;
    private String activity;
    private String dsbCleavageType;
    private String target_sequence;
    private String proteinFormat;
    private String source;

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

    public String getProteinFormat() {
        return proteinFormat;
    }

    public void setProteinFormat(String proteinFormat) {
        this.proteinFormat = proteinFormat;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
