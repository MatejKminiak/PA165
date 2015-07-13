package cz.fi.muni.pa165.dto;

import cz.fi.muni.pa165.entity.Product;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class CategoryDTO
{
    private Long id;

    private String name;

    private Set<ProductDTO> products = new HashSet<>();

    public String getName() {
        return name;
    }

    protected void addProduct(ProductDTO p){
        products.add(p);
    }
    public void setName(String name) {
        this.name = name;
    }

    public Set<ProductDTO> getProducts() {
        return Collections.unmodifiableSet(products);
    }

    public Long getId() {
        return id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CategoryDTO other = (CategoryDTO) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }
}
