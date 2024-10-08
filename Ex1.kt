package com.hamza.seance1.TD4

open class Produit(val id: Int, var prix: Double) {

    constructor(code: Int): this(code, 0.0)

    open fun prixProduit(): Double {
        return prix
    }

    override fun toString(): String {
        return "code: $id; prix d'origine: $prix"
    }

    override fun equals(other: Any?): Boolean {
        if(other is Produit) return this.id == other.id
        return false
    }
}

class ProduitEnSolde(id: Int, prix: Double, var remise: Double): Produit(id, prix) {
    init {
        if(remise < 0 || remise > 90) throw IllegalArgumentException("La remise doit extre comprise entre 0 et 90 !!")
    }

    override fun prixProduit(): Double {
        return prix * (1 - remise) / 100
    }

    override fun toString(): String {
        return super.toString() + "; remise: $remise"
    }
}

class Boutique {
    private val produits = mutableListOf<Produit>()

    fun indiceDe(code: Int): Int {
        for (i in produits.indices) {
            if (produits[i].id == code) return i
        }
        return -1
    }

    fun ajouter(p: Produit) {
        if (produits.contains(p)) {
            throw Exception("Le produit existe deja dans la boutique")
        }
        produits.add(p)
    }

    fun supprimer(code: Int): Boolean {
        val indice = indiceDe(code)
        if (indice != -1) {
            produits.removeAt(code)
            return true
        }
        return false
    }

    fun supprimer(p: Produit): Boolean {
        return produits.remove(p)
    }

    fun nombreProduitsEnSolde(): Int {
        return produits.filterIsInstance<ProduitEnSolde>().size
    }
}

fun main() {
    val p0 = Produit(0, 9.99)
    val p1 = Produit(1, 19.99)
    val p2 = Produit(2, 29.99)
    val p3 = Produit(3, 39.99)
    val p4 = Produit(4, 49.99)
    val p00 = ProduitEnSolde(0, 10.00, 20.0)

    println(p0.prixProduit())
    println(p0.toString())
    println(p0 == p1)
    println(p0 == p00)

    println(p1.prixProduit())
    println(p1.toString())

    val b0 = Boutique()
    b0.ajouter(p0)
    b0.ajouter(p00)
    b0.ajouter(p1)
    b0.ajouter(p2)
    b0.ajouter(p3)
    b0.ajouter(p4)
    b0.ajouter(p4)

    println(b0.supprimer(0))
    println(b0.supprimer(0))

    println(b0.supprimer(p00))
    println(b0.supprimer(p00))

    println(b0.nombreProduitsEnSolde())
}