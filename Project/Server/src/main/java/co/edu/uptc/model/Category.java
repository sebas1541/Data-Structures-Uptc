package co.edu.uptc.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class Category implements Serializable {
    private String categoryId;
    private String name;
    private String description;

    // Predefined category names as a static list
    private static final List<String> VALID_CATEGORIES = Arrays.asList(
            "Alimentación", "Transporte", "Vivienda", "Salud",
            "Entretenimiento", "Ropa y Calzado", "Otros Gastos"
    );

    public Category(String categoryId, String name, String description) {
        this.categoryId = categoryId;
        this.name = name;
        this.description = description;
    }

    // Method to check if the category name is valid
    public static boolean isValidCategory(String categoryName) {
        return VALID_CATEGORIES.contains(categoryName);
    }

    // Getters and Setters
    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId='" + categoryId + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public void updateCategory(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category otherCategory = (Category) o;
        return categoryId.equals(otherCategory.categoryId);
    }

    public int hashCode() {
        return categoryId.hashCode();
    }
}
