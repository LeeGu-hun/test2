package controller;

public class ActionForward {
	//view에 넘겨줄 자료의 유무와 uri로 보여주면 false, url로는 true 
	private boolean isRedirect=false; 
	private String path=null; //실제 보여줄 페이지 주소
	
	public boolean isRedirect() {return isRedirect;}
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
	public String getPath() {return path;}
	public void setPath(String path) {this.path = path;}
	
}
