package fr.diginamic.service;

import fr.diginamic.entities.*;
import fr.diginamic.mochizukiTools.Utils;
import fr.diginamic.service.Logging;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;


public class Parser {
    private static final Logger LOG;
    //private static ArrayList<String[]> rowsToInsert;
    public static final int CATEGORIE = 0;
    public static final int MARQUE = 1;
    public static final int PRODUIT = 2;
    public static final int NUTRITIONGRADEFR = 3;
    public static final int INGREDIENTS = 4;
    public static final int ENERGIE100G = 5;
    public static final int GRAISSE100G = 6;
    public static final int SUCRES100G = 7;
    public static final int FIBRES100G = 8;
    public static final int PROTEINES100G = 9;
    public static final int SEL100G = 10;
    public static final int VITA100G = 11;
    public static final int VITD100G = 12;
    public static final int VITE100G = 13;
    public static final int VITK100G = 14;
    public static final int VITC100G = 15;
    public static final int VITB1100G = 16;
    public static final int VITB2100G = 17;
    public static final int VITPP100G = 18;
    public static final int VITB6100G = 19;
    public static final int VITB9100G = 20;
    public static final int VITB12100G = 21;
    public static final int CALCIUM100G = 22;
    public static final int MAGNESIUM100G = 23;
    public static final int IRON100G = 24;
    public static final int FER100G = 25;
    public static final int BETACAROTENE100G = 26;
    public static final int PRESENCEHUILEPALME = 27;
    public static final int ALLERGENES = 28;
    public static final int ADDITIFS = 29;

    private static HashMap<Integer, String> hashMap_categories, hashMap_nutriscore, hashMap_marque, hashMap_produits, hashMap_ingredients, hashMap_allergenes, hashMap_additifs;


    static {
        LOG = Logging.LOG;
        hashMap_categories = new HashMap<>();
        hashMap_nutriscore = new HashMap<>();
        hashMap_marque = new HashMap<>();
        hashMap_produits = new HashMap<>();
        hashMap_ingredients = new HashMap<>();
        hashMap_allergenes = new HashMap<>();
        hashMap_additifs = new HashMap<>();
    }

    public static void insertToDataBase(ArrayList<String[]> rows) {
        int rowNbr = 0;
        Iterator<String[]> rowsIter = rows.iterator();
        EntityManager em = null;
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("IBOOF-JPA");) {
            em = emf.createEntityManager();
            em.getTransaction().begin();

            while (rowsIter.hasNext()) {
                rowNbr++;
                String[] row = rowsIter.next();
                for (int i = 0; i < row.length; i++)
                    instance_secureReferencement(row, i, rowNbr, em);
            }
            em.getTransaction().commit();
            em.close();
        } catch (Exception e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public static int instance_secureReferencement(String[] row, int columnIndex, int rowIndex, EntityManager em) {
        boolean presenceDoublon = false;
        switch (columnIndex) {
            case CATEGORIE:
//                Utils.msgInfo(String.format("Vérification de la présence d'un doublon dans le hashMap CATEGORIE"));
                if (hashMap_categories.containsValue(row[CATEGORIE])) {
                    presenceDoublon = true;
                } else {
                    presenceDoublon = false;
//                    Utils.msgResult("aucun doublon, référencement de l'objet");
                    hashMap_categories.put(columnIndex, row[CATEGORIE]);
                    Categorie categorie = new Categorie(row[CATEGORIE]);
                    if (categorie != null) em.persist(categorie);
                }
                break;

            case MARQUE:
//                Utils.msgInfo(String.format("Vérification de la présence d'un doublon dans le hashMap MARQUE"));
                if (hashMap_marque.containsValue(row[MARQUE])) {
                    presenceDoublon = true;
                } else {
                    presenceDoublon = false;
//                    Utils.msgResult("aucun doublon, référencement de l'objet");
                    hashMap_marque.put(columnIndex, row[MARQUE]);
                    Marque marque = new Marque(row[MARQUE]);
                    if (marque != null) em.persist(marque);
                }
                break;

            case PRODUIT:
//                Utils.msgInfo(String.format("Vérification de la présence d'un doublon dans le hashMap PRODUIT"));
                if (hashMap_produits.containsValue(row[PRODUIT])) {
                    presenceDoublon = true;
                } else {
                    presenceDoublon = false;
//                    Utils.msgResult("aucun doublon, référencement de l'objet");
                    hashMap_produits.put(columnIndex, row[PRODUIT]);
                    Produit produit = new Produit(row[PRODUIT]);
                    //!!!!IL fAUT RENTRER ICI TOUS LES ATTRIBUTS DE PRODUIT
                    if (produit != null) em.persist(produit);
                }
                break;

            case NUTRITIONGRADEFR:
//                Utils.msgInfo(String.format("Vérification de la présence d'un doublon dans le hashMap NUTRITIONGRADEFR"));
                if (hashMap_nutriscore.containsValue(row[NUTRITIONGRADEFR])) {
                    presenceDoublon = true;
                } else {
                    presenceDoublon = false;
//                    Utils.msgResult("aucun doublon, référencement de l'objet");
                    hashMap_nutriscore.put(columnIndex, row[NUTRITIONGRADEFR]);
                    Nutriscore nutriscore = new Nutriscore(row[NUTRITIONGRADEFR].charAt(0));
                    if (nutriscore != null) em.persist(nutriscore);
                }
                break;

            case INGREDIENTS:
//                Utils.msgInfo(String.format("Vérification de la présence d'un doublon dans le hashMap INGREDIENTS"));
                if (hashMap_ingredients.containsValue(row[INGREDIENTS])) {
                    presenceDoublon = true;
                } else {
                    presenceDoublon = false;
//                    Utils.msgResult("aucun doublon, référencement de l'objet");
                    hashMap_ingredients.put(columnIndex, row[INGREDIENTS]);
                    Ingredient ingredient = new Ingredient(row[INGREDIENTS]);
                    if (ingredient != null) em.persist(ingredient);
                }
                break;

            case ALLERGENES:
//                Utils.msgInfo(String.format("Vérification de la présence d'un doublon dans le hashMap ALLERGENES"));
                if (hashMap_allergenes.containsValue(row[ALLERGENES])) {
                    presenceDoublon = true;
                } else {
                    presenceDoublon = false;
//                    Utils.msgResult("aucun doublon, référencement de l'objet");
                    hashMap_allergenes.put(columnIndex, row[ALLERGENES]);
                    Allergene allergene = new Allergene(row[ALLERGENES]);
                    if (allergene != null) em.persist(allergene);
                }
                break;

            case ADDITIFS:
//                Utils.msgInfo(String.format("Vérification de la présence d'un doublon dans le hashMap ADDITIFS"));
                if (hashMap_additifs.containsValue(row[ADDITIFS])) {
                    presenceDoublon = true;
                } else {
                    presenceDoublon = false;
//                    Utils.msgResult("aucun doublon, référencement de l'objet");
                    hashMap_additifs.put(columnIndex, row[ADDITIFS]);
                    Additif additif = new Additif(row[ADDITIFS]);
                    if (additif != null) em.persist(additif);
                }
                break;
            default:
//                Utils.msgInfo("aucune table affecté à cette valeur : l'instantiation est caduc");
                return 1;
        }

//        if (presenceDoublon) Utils.msgResult("doublon détecté");
//        else Utils.msgResult("aucun doublon, la valeur a été référencée");

        return presenceDoublon ? 1 : 0;
    }
}
