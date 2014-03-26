
/**
 * Denna klass representerar noderna i ett bin�rt s�ktr�d utan dubletter.
 * 
 * Detta �r den enda av de tre klasserna ni ska g�ra n�gra �ndringar i. (Om ni
 * inte vill l�gga till fler testfall.) De �ndringar som �r till�tna �r dock
 * begr�nsade av f�ljande regler:
 * <ul>
 * <li>Ni f�r INTE l�gga till n�gra fler instansvariabler.
 * <li>Ni f�r INTE l�gga till n�gra statiska variabler.
 * <li>Ni f�r INTE anv�nda n�gra loopar n�gonstans.
 * <li>Ni F�R l�gga till fler metoder, dessa ska d� vara privata.
 * </ul>
 * 
 * @author henrikbe
 * 
 * @param <T>
 */
public class BinarySearchTreeNode<T extends Comparable<T>> {

	private T data;
	private BinarySearchTreeNode<T> left;
	private BinarySearchTreeNode<T> right;

	public BinarySearchTreeNode(T data) {
		this.data = data;
	}

	/**
	 * L�gger till en nod i det bin�ra s�ktr�det. Om noden redan existerar s�
	 * l�mnas tr�det of�r�ndrat.
	 * 
	 * @param data
	 *            datat f�r noden som ska l�ggas till.
	 * @return true om en ny nod lades till tr�det.
	 */
	public boolean add(T data) {
		
		if(contains(data))
			return false;
		
		int res = data.compareTo(this.data);
		
		if(res < 0){
			if(left == null)
				left = new BinarySearchTreeNode<T>(data);
			else
				left.add(data);
		}else if(res > 0){
			if(right == null)
				right = new BinarySearchTreeNode<T>(data);
			else
				right.add(data);
		}
		return true;
	}

	/**
	 * Privat hj�lpmetod som �r till nytta vid borttag. Ni beh�ver inte
	 * skriva/utnyttja denna metod om ni inte vill.
	 * 
	 * @return det minsta elementet i det (sub)tr�d som noden utg�r root i.
	 */
	private T findMin() {		
		return findMin(this);
	}

	private T findMin(BinarySearchTreeNode<T> n){
		if(n == null)
			return null;
		else if(n.left == null)
			return n.data;
		return findMin(n.left);
	}
	private T findMax(BinarySearchTreeNode<T> n){
		if(n == null)
			return null;
		else if(n.right == null)
			return n.data;
		return findMax(n.right);
	}
	

	/**
	 * Tar bort ett element ur tr�det. Om elementet inte existerar s l�mnas
	 * tr�det of�r�ndrat.
	 * 
	 * @param data
	 *            elementet som ska tas bort ur tr�det.
	 * @return en referens till nodens subtr�d efter borttaget.
	 */
	public BinarySearchTreeNode<T> remove(T data) {
		return remove(data,this);
	}
	
	private BinarySearchTreeNode<T> remove(T data, BinarySearchTreeNode<T> n){
		if(n == null)
			return n;
		
		int res = data.compareTo(n.data);
		
		if(res < 0)
			n.left = remove(data,n.left);
		else if(res > 0)
			n.right = remove(data,n.right);
		else if(n.left != null && n.right != null){
			n.data = findMin(n.right);
			n.right = remove(n.data,n.right);
		}
		
		else if(n.left != null)
			n = n.left;
		else
			n = n.right;
		
		return n;
	}

	/**
	 * Kontrollerar om ett givet element finns i det (sub)tr�d som noden utg�r
	 * root i.
	 * 
	 * @param data
	 *            det s�kta elementet.
	 * @return true om det s�kta elementet finns i det (sub)tr�d som noden utg�r
	 *         root i.
	 */
	public boolean contains(T data) {		
		return contains(data,this);
	}
	
	private boolean contains(T data, BinarySearchTreeNode<T> n){
		
		if(n == null)
			return false;
		
		int res = data.compareTo(n.data);
		
		if(res < 0)
			return contains(data,n.left);
		else if(res > 0)
			return contains(data,n.right);
		else
			return true;
	}

	/**
	 * Storleken p� det (sub)tr�d som noden utg�r root i.
	 * 
	 * @return det totala antalet noder i det (sub)tr�d som noden utg�r root i.
	 */
	
	public int size() {		
		return getSize(this,0);
	}
	
	private int getSize(BinarySearchTreeNode<T> n, int count){
		if(n== null){
			return 0;
		}
		count = 1 + getSize(n.left,count) + getSize(n.right,count);
		return count;
	}

	/**
	 * Det h�gsta djupet i det (sub)tr�d som noden utg�r root i.
	 * 
	 * @return djupet.
	 */
	public int depth() {
		return getDepth(this);
	}
	
	private int getDepth(BinarySearchTreeNode<T> n){
		if(n == null)
			return -1;
		else 
			return 1 + Math.max(getDepth(n.left), getDepth(n.left));
	}
	
	/**
	 * Returnerar en str�ngrepresentation f�r det (sub)tr�d som noden utg�r root
	 * i. Denna representation best�r av elementens dataobjekt i sorterad
	 * ordning med ", " mellan elementen.
	 * 
	 * @return str�ngrepresentationen f�r det (sub)tr�d som noden utg�r root i.
	 */
	public String toString() {
		return printTree(this, new StringBuilder());
	}
	
	private String printTree(BinarySearchTreeNode<T> n, StringBuilder ret){
		
		if(n != null){
			
			if(n.left != null){
				printTree(n.left,ret);
				ret.append(", ");
			}
			
			ret.append(n.data);
			
			if(n.right != null){
				ret.append(", ");
				printTree(n.right,ret);
			}
		}
		return ret.toString();
	}
}