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
    private String accession;
    private String pamPreference;
    private String substrateTarget;
    private String activity;
    private String overhang;
    private String note;
    private String reference;
    private String addGeneLink;
    private String fusion;
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

    public String getAccession() {
        return accession;
    }

    public void setAccession(String accession) {
        this.accession = accession;
    }

    public String getSubstrateTarget() {
        return substrateTarget;
    }

    public void setSubstrateTarget(String substrateTarget) {
        this.substrateTarget = substrateTarget;
    }

    public String getOverhang() {
        return overhang;
    }

    public void setOverhang(String overhang) {
        this.overhang = overhang;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getAddGeneLink() {
        return addGeneLink;
    }

    public void setAddGeneLink(String addGeneLink) {
        this.addGeneLink = addGeneLink;
    }
}
