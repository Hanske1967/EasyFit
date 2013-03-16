package be.fortemaison.easyfit.model;

/**
 * Created with IntelliJ IDEA.
 * User: hansk_000
 * Date: 14/03/13
 * Time: 21:37
 * To change this template use File | Settings | File Templates.
 */
public class FavoriteProduct extends CommonAncestor {

    private ProductAncestor productAncestor;

    private User user;

    public FavoriteProduct (ProductAncestor productAncestor, User user) {

        this.productAncestor = productAncestor;
        this.user = user;
    }

    public FavoriteProduct () {
        //
    }

    public ProductAncestor getProductAncestor () {
        return productAncestor;
    }

    public void setProductAncestor (ProductAncestor productAncestor) {
        this.productAncestor = productAncestor;
    }

    public User getUser () {
        return user;
    }

    public void setUser (User user) {
        this.user = user;
    }
}
