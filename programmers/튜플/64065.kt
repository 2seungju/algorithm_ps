class Solution {
    fun solution(s: String): IntArray {
        val charArray: CharArray = s.toCharArray();

        var tupleList: MutableList<String> = ArrayList();

        var tuple = "";

        for (i in 1 until charArray.size - 1) {
            tuple = tuple.plus(charArray[i]);

            if (charArray[i] == '}') {
                if (tuple.toCharArray()[0] == ',') {
                    tupleList.add(tuple.substring(1));

                } else {
                    tupleList.add(tuple);
                }

                tuple = ""
            }
        }

        tupleList = tupleList.sortedBy { it.length }.toMutableList();

        val map: MutableMap<String, Boolean> = HashMap();
        var result: MutableList<Int> = ArrayList();

        for (str in tupleList) {
            val tempArray: List<String> = str.substring(1, str.length - 1).split(",");

            for (temp in tempArray) {
                if (map[temp] == null) {
                    map[temp] = true;

                    result.add(temp.toInt());
                }
            }
        }

        return result.toIntArray();
    }
}