public class ALforMap {
	public static node head = null, tail = null;
	public node links;
	private Object first, second;
	private double roadExpenss , cost;
	static int c = 1;

	public ALforMap(Object a, Object b , double c) {
		first = a;
		second = b;
		roadExpenss=c;
		work();
	}
	
	public ALforMap(Object a , double c) {
		first = a;
		cost=c;
		outTheCostOnTheArea();
	}

	public ALforMap() {

	}
	
	private void outTheCostOnTheArea(){
		for(node n=head;n!=null;n=n.area){
			if(n.element.equals(first)){
				n.cost=cost;
				break;
			}
		}
	}

	private void work() {
		if (head == null) {
			node location1 = new node(first, null, null,0);
			node location2 = new node(second, null, null,0);

			node linkingArea1 = new node(location2, null,roadExpenss);
			node linkingArea2 = new node(location1, null,roadExpenss);

			location1.links = linkingArea1;
			location2.links = linkingArea2;

			location1.area = location2;

			head = location1;
			tail = location2;

		} else {
			if (!first.equals(null) && !second.equals(null)) {
				if (!isCreated(first) && !isCreated(second)) {
					node location1 = new node(first, null, null,0);
					node location2 = new node(second, null, null,0);

					tail.area = location1;
					tail = location1;

					tail.area = location2;
					tail = location2;

					node linkingArea1 = new node(location2, null,roadExpenss);
					node linkingArea2 = new node(location1, null,roadExpenss);

					location1.links = linkingArea1;
					location2.links = linkingArea2;

				} else if (!first.equals(null) && !isCreated(first)) {
					node location1 = new node(first, null, null,0);
					tail.area = location1;
					tail = location1;

					node save = getAreaLink(second);

					node linkingArea1 = new node(save, null,roadExpenss);
					node linkingArea2 = new node(location1, null,roadExpenss);
					location1.links = linkingArea1;
					getLastElement(save).linkingArea = linkingArea2;

				} else if (!second.equals(null) && !isCreated(second)) {
					node location1 = new node(second, null, null,0);
					tail.area = location1;
					tail = location1;

					node save = getAreaLink(first);

					node linkingArea1 = new node(save, null,roadExpenss);
					node linkingArea2 = new node(location1, null,roadExpenss);
					location1.links = linkingArea1;
					getLastElement(save).linkingArea = linkingArea2;

				} else if (isCreated(first) && isCreated(second)) {
					node area1 = getAreaLink(first);
					node area2 = getAreaLink(second);

					node link1 = getLastElement(area1);
					node link2 = getLastElement(area2);

					node linkingArea1 = new node(area2, null,roadExpenss);
					node linkingArea2 = new node(area1, null,roadExpenss);

					link1.linkingArea = linkingArea1;
					link2.linkingArea = linkingArea2;

				}
			}
		}
	}

	private node getAreaLink(Object value) {
		for (node n = head; n != null; n = n.area) {
			if (n.element.equals(value)) {
				return n;
			}
		}
		return null;
	}

	private node getLastElement(node value) {
		for (node n = value.links; n != null; n = n.linkingArea) {
			if (n.linkingArea == null) {
				return n;
			}
		}
		return null;
	}

	private boolean isCreated(Object value) {
		for (node n = head; n != null; n = n.area) {
			if (n.element.equals(value)) {
				return true;
			}
		}
		return false;
	}

	public void printAL() {
		int c = 1;
		for (node n = head; n != null; n = n.area) {
			System.out.print(c++ + " = " + n.element + "("+n.cost+") => ");
			for (node m = n.links; m != null; m = m.linkingArea) {
				if (m.linkingArea != null)
					System.out.print(m.areaZone.element + "("+m.roadExpenss+") => ");
				else
					System.out.print(m.areaZone.element+"("+m.roadExpenss+")");
			}
			System.out.println();
		}
	}

	public int totalNode() {
		int count = 0;
		for (node n = head; n != null; n = n.area) {
			count++;
		}
		return count;
	}
}
