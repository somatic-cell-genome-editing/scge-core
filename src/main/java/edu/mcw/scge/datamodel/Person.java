package edu.mcw.scge.datamodel;

import java.util.List;

/**
 * Created by jthota on 8/20/2019.
 */
public class Person {
    private int id;
    private String name_lc;
    private String name;
    private int institution;
    private String institutionName;
    private String email;
    private String email_lc;
    private String otherId;
    private String grantTitle;
    private String address;
    private String phone;
    private int grantId;
    private String googleSub;
    private String createdDate;
    private String modifiedBy;
    private String modifiedById;
    private String modifiedDate;
    private String status;
    private List<String> groups;
    private List<String> roles;
    private String pi;
    private String firstName;
    private String lastName;

    private Person(Builder builder){
        this.id=builder.id;
        this.name=builder.name;
        this.institution=builder.institution;
        this.institutionName=builder.institutionName;
        this.email=builder.email;
        this.otherId=builder.otherId;
        this.address=builder.address;
        this.phone=builder.phone;
        this.googleSub=builder.googleSub;
        this.createdDate=builder.createdDate;
        this.modifiedBy=builder.modifiedBy;
        this.modifiedById=builder.modifiedById;
        this.modifiedDate=builder.modifiedDate;
        this.status=builder.status;
        this.groups=builder.groups;
        this.roles=builder.roles;
        this.pi=builder.pi;
        this.grantId=builder.grantId;
        this.name_lc=builder.name_lc;
        this.email_lc=builder.email_lc;
        this.firstName=builder.firstName;
        this.lastName=builder.lastName;
    }
    public static class Builder{
        private int id;
        private String name;
        private String name_lc;
        private int institution;
        private String institutionName;
         private String email;
        private String otherId;
        private String email_lc;
        private String address;
        private int grantId;
        private String phone;
        private String googleSub;
        private String createdDate;
        private String modifiedBy;
        private String modifiedById;
        private String modifiedDate;
        private String status;
        private List<String> groups;
        private List<String> roles;
        private String pi;
        private String firstName;
        private String lastName;
        public Builder id(int id){
            this.id=id;
            return this;
        }
        public Builder   firstName(String firstName){
            this.firstName=firstName;
            return this;
        }
        public Builder   lastName(String lastName){
            this.lastName=lastName;
            return this;
        }
        public Builder   name(String name){
            this.name=name;
            return this;
        }
        public Builder   name_lc(String name_lc){
            this.name_lc=name_lc;
            return this;
        }
        public Builder   institution(int institution){
            this.institution=institution;
            return this;
        }
        public Builder   institutionName(String institutionName){
            this.institutionName=institutionName;
            return this;
        }
        public Builder   email(String email){
            this.email=email;
            return this;
        }
        public Builder   email_lc(String email_lc){
            this.email_lc=email_lc;
            return this;
        }
        public Builder   otherId(String otherId){
            this.otherId=otherId;
            return this;
        }
        public Builder address(String address){
            this.address=address;
            return this;
        }
        public Builder  phone(String phone){
            this.phone=phone;
            return this;
        }
        public Builder  grantId(int grantId){
            this.grantId=grantId;
            return this;
        }
        public Builder  googleSub(String googleSub){
            this.googleSub=googleSub;
            return this;
        }

        public Builder  createdDate(String createdDate){
            this.createdDate=createdDate;
            return this;
        }
        public Builder  modifiedBy(String modifiedBy){
            this.modifiedBy=modifiedBy;
            return this;
        }
        public Builder  modifiedById(String modifiedById){
            this.modifiedById=modifiedById;
            return this;
        }
        public Builder   modifiedDate(String modifiedDate){
            this.modifiedDate=modifiedDate;
            return this;
        }
        public Builder status(String status){
            this.status=status;
            return  this;
        }
        public Builder groups(List<String> groups){
            this.groups=groups;
            return  this;
        }
        public Builder roles(List<String> roles){
            this.roles=roles;
            return this;
        }

        public Builder pi(String pi){
            this.pi=pi;
            return this;
        }
        public Person build(){
            return new Person(this);
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getOtherId() {
        return otherId;
    }

    public void setOtherId(String otherId) {
        this.otherId = otherId;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public String getName_lc() {
        return name_lc;
    }

    public void setName_lc(String name_lc) {
        this.name_lc = name_lc;
    }

    public String getEmail_lc() {
        return email_lc;
    }

    public void setEmail_lc(String email_lc) {
        this.email_lc = email_lc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getModifiedById() {
        return modifiedById;
    }

    public void setModifiedById(String modifiedById) {
        this.modifiedById = modifiedById;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public int getInstitution() {
        return institution;
    }

    public void setInstitution(int institution) {
        this.institution = institution;
    }

    public int getGrantId() {
        return grantId;
    }

    public void setGrantId(int grantId) {
        this.grantId = grantId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGoogleSub() {
        return googleSub;
    }

    public void setGoogleSub(String googleSub) {
        this.googleSub = googleSub;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getGroups() {
        return groups;
    }

    public void setGroups(List<String> groups) {
        this.groups = groups;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getPi() {
        return pi;
    }

    public void setPi(String pi) {
        this.pi = pi;
    }

    public String getGrantTitle() {
        return grantTitle;
    }

    public void setGrantTitle(String grantTitle) {
        this.grantTitle = grantTitle;
    }
}
