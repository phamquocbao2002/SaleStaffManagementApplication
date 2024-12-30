package model.function;

public class substringchecker {

	public substringchecker() {
		// TODO Auto-generated constructor stub
	}

	public boolean checksubstring(String parent, String child) {
		String[] parent_ar = parent.split(" ");
		String[] child_ar = child.split(" ");
		boolean found = false;
		if (parent_ar.length == 1) {
			if (child.length() >= 3) {
				if (parent.contains(child)) {
					found = true;
				}
			}

		} else {
			for (int i = 0; i < child_ar.length; i++) {
				for (String word : parent_ar) {
					if (word.equals(child_ar[i])) {
						found = true;
						break;
					} else {
						found = false;
					}
				}

				if (found == false) {
					break;
				}
			}
		}

		return found;

	}

}
