package enumtype;

/**
 * @author Created by lenovo
 * @date 2022/5/30 11:57
 */
public enum DataSourceEnum {
	MASTER("MASTER"),
	SLAVE("SLAVE");
	String target;
	DataSourceEnum(String target){
		this.target = target;
	}
  public String getDataSourceName(){
		return this.target;
  }
}
