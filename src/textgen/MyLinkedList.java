package textgen;

import java.util.AbstractList;


public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	public MyLinkedList() {
		head = new LLNode<E>(null);
		tail = new LLNode<E>(null);
		head.next = tail;
		tail.prev = head;
		size = 0;
	}

	public boolean add(E element ) 
	{
		if (element == null) throw new NullPointerException();
		LLNode<E> node = new LLNode<E>(element);
		if (size==0)   head = node;
		else {
			tail.next = node;
			node.prev = tail;
			}		
		tail = node;
		size++;
		return true;
		}
	
	public E get(int index) 
	{
		 if (index >= 0 && index <= size-1) {
			 LLNode<E> node = head;
			 for (int i=0; i<index; i++) {
				 node=node.next;
			 }
			 return node.data;
		 }
		 else throw new IndexOutOfBoundsException();
	}

	public void add(int index, E element ) 
	{
		 if (element == null) throw new NullPointerException();
		 if (index < 0 || index > size) throw new IndexOutOfBoundsException();
		 if(head == null) {
			 add(element);
			 return;
			 } 
		 
		 LLNode<E> node = head;
		 for (int i = 0; i < index-1; i++) {
				node = node.next;
			}
		 
		 LLNode<E> nodeNew = new LLNode<>(element);
		 nodeNew.next = node.next;
		 if (nodeNew.next != null) {nodeNew.next.prev = nodeNew;} 
		 else {tail = nodeNew;}
		
		 nodeNew.prev = node;
		 node.next = nodeNew;
		 size++;
		
	}

	public int size() {
		return size;
	}

	public E remove(int index) 
	{
		 if (index < 0 || index > size - 1) throw new IndexOutOfBoundsException();
		 if (index == 0) {
	            LLNode<E> node = head;
	            
	            if(node.next == null) {
	                head = null;
	                tail = null;
	                size--;
	                return node.data;
	            }
	            
	            head = head.next;
	            head.prev = null;
	            node.next = null;
	            size--;
	            return node.data;
	        }

	        LLNode<E> node = head;
	        for (int i=0; i<index-1; i++) {
	        	node = node.next;
	        }
 
	        LLNode<E> del = node.next;
	        if (del.next == null) {
	            tail = node;
	            node.next = null;
	            del.prev = null;
	            return del.data;
	        }
	        node.next = del.next;
	        del.next.prev = node;
	        del.next = null;
	        del.prev = null;
	        size--;
	        return del.data;
	}

	public E set(int index, E element) 
	{
		if (element == null) throw new NullPointerException();
		if (index < 0 || index > size) throw new IndexOutOfBoundsException();
		LLNode<E> node = head;
		for (int i = 0; i < index; i++) {
			node = node.next;
			}
		node.data = element;
		return node.data;

		}   
}


class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}
