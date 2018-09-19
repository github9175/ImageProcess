public class DLnode{
	short red;
	short green;
	short blue;
	int length;
	DLnode next;
	DLnode prev;
public DLnode(){
	red=0;
	green=0;
	blue=0;
	length=0;
	next=null;
	prev=null;
}
public DLnode(short red, short green, short blue, int length){
    this.red=red;
    this.green=green;
    this.blue=blue;
    this.length=length;
    next=null;
    prev=null;
}
public void insertafter(short red, short green, short blue, int length){
    DLnode end=new DLnode(red, green, blue,length);
    end.prev=this;
    next=end;
}
public void insert(short red, short green, short blue, int postlength){
    DLnode middle=new DLnode(red, green, blue,1);
    this.length=length-postlength-1;
    DLnode rest=new DLnode(this.red, this.green, this.blue,postlength);
    middle.next=rest;
    rest.next=this.next;
    this.next=middle;
    //System.out.println("this "+this.red+" "+"middle "+middle.red+" "+"rest "+rest.red+" ");
    //System.out.println("this "+this.length+" "+"middle "+middle.length+" "+"rest "+rest.length+" ");
    
    if(rest.next!=null){
    	rest.next.prev=rest;
    }
    rest.prev=middle;
    middle.prev=this;
    //System.out.println(this.next.next==rest);
    //System.out.println(rest.prev.prev==this);
    if (this.length==0){
    	this.length=middle.length;
    	this.next=middle.next;
    	this.next.prev=this;
    	this.red=middle.red;
    	this.green=middle.green;
    	this.blue=middle.blue;
    	middle=this;
    }
   
    if(rest.length==0){
    	if(rest.next!=null){
    	middle.next=rest.next;
    	rest.next.prev=middle;
    }else{
    	middle.next=null;
    }
    }
    //System.out.println("this "+this.red+" "+"middle "+this.next.red+" "+"second "+this.next.next.red+" ");
    
    if (middle.prev!=null&&middle.prev.red==middle.red&&middle.prev.green==middle.green&&middle.prev.blue==middle.blue){
    	middle.prev.length+=1;
    	middle.prev.next=middle.next;
    	if(middle.next!=null){
    	middle.next.prev=middle.prev;
    }
    	middle=middle.prev;
    }

    if(middle.next!=null&&middle.next.red==middle.red&&middle.next.green==middle.green&&middle.next.blue==middle.blue){
    	middle.length+=1;
    	middle.next=middle.next.next;
    	if (middle.next!=null){
    	middle.next.prev=middle;
    }
    }
    //System.out.println("final "+this.red+"");
}

}
