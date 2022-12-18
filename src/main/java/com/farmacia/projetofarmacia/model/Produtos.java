package com.farmacia.projetofarmacia.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
@Table(name = "tb_produtos")
public class Produtos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome não pode ser nulo")
    @Size(min = 1, max = 255, message = "Tamanho mínimo de 1 à 255 caracteres")
    private String nome;

    @NotNull(message = "Quantidade não pode ser nulo")
    private int quantidade;

    @NotNull(message = "Controlado não pode ser nulo")
    private boolean eControlado;

    @NotNull(message = "Peso não pode ser nulo")
    private BigDecimal peso;

    @NotNull(message = "Valor não pode ser nulo")
    private BigDecimal valor;

    @ManyToOne
    @JsonIgnoreProperties("produto")
    private Categoria categoria;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public boolean iseControlado() {
        return eControlado;
    }

    public void seteControlado(boolean eControlado) {
        this.eControlado = eControlado;
    }

    public BigDecimal getPeso() {
        return peso;
    }

    public void setPeso(BigDecimal peso) {
        this.peso = peso;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
