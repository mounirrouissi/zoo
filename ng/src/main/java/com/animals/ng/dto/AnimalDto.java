package com.animals.ng.dto;

public class AnimalDto {
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    private Long id;
    private String name;
    private String type;

    public AnimalDto(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.type = builder.type;
    }

    public void setName(String s) {
        this.name = s;
    }

    public static class Builder {

        private Long id;
        private String name;
        private String type;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public AnimalDto build() {
            return new AnimalDto(this);
        }

    }

}
