package com.encyclopediagalactica.document.dto;

public class DocumentDto {

    private Long id;
    private String name;
    private String desc;

    private DocumentDto(Long id, String name, String desc) {
        this.id = id;
        this.name = name;
        this.desc = desc;
    }

    private DocumentDto() {
    }

    public static DocumentDtoBuilder builder() {
        return new DocumentDtoBuilder();
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static class DocumentDtoBuilder {
        private Long id;
        private String name;
        private String desc;

        public DocumentDtoBuilder() {
        }

        public DocumentDtoBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public DocumentDtoBuilder name(String name) {
            this.name = name;
            return this;
        }

        public DocumentDtoBuilder desc(String desc) {
            this.desc = desc;
            return this;
        }

        public DocumentDto build() {
            return new DocumentDto(this.id, this.name, this.desc);
        }

        public String toString() {
            return "DocumentDto.DocumentDtoBuilder(id=" + this.id + ", name=" + this.name + ", desc=" + this.desc + ")";
        }
    }
}
