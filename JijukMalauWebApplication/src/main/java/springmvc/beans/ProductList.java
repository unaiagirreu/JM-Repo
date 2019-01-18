
package springmvc.beans;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import javax.persistence.TypedQuery;

public class ProductList {
	List <BigInteger> list1;
	List <BigDecimal> list2;
	List <BigDecimal> list3;
	List <BigDecimal> list4;

	
	public ProductList(){}
	
	public List<BigInteger> getList1() {
		return this.list1;
	}
	public List<BigDecimal> getList2() {
		return this.list2;
	}
	public List <BigDecimal> getList3() {
		return this.list3;
	}
	public List <BigDecimal> getList4() {
		return this.list4;
	}
	public void setList1(List <BigInteger> list1){
		this.list1 = list1;
	}
	public void setList2(List <BigDecimal> list2){
		this.list2 = list2;
	}
	public void setList3(List <BigDecimal> list3){
		this.list3 = list3;
	}
	public void setList4(List <BigDecimal> list4){
		this.list4 = list4;
	}

	
	
}
