package com.zipcodewilmington.arrayutility;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by leon on 3/6/18.
 */
public class ArrayUtility<T> {
    private T[] array;

    public ArrayUtility(T[] array) {
        this.array = array;
    }

    public Integer countDuplicatesInMerge(T[] arrayToMerge, T valueToEvaluate) {
        T[] mergedArray = Arrays.copyOf(array, array.length + arrayToMerge.length);
        System.arraycopy(arrayToMerge, 0, mergedArray, array.length, arrayToMerge.length);
        return countDuplicatesInArray(mergedArray, valueToEvaluate);
    }

    private Integer countDuplicatesInArray(T[] array, T valueToEvaluate) {
        return (int) Arrays.stream(array).filter(obj -> obj.equals(valueToEvaluate)).count();
    }

    public T getMostCommonFromMerge(T[] arrayToMerge) {
        T[] mergedArray = Arrays.copyOf(array, array.length + arrayToMerge.length);
        System.arraycopy(arrayToMerge, 0, mergedArray, array.length, arrayToMerge.length);
        return getMostCommonInArray(mergedArray);
    }

    private T getMostCommonInArray(T[] array) {
        Map<T, Integer> elementCountMap = getElementCountMap(array);
        return getElementWithHighestValueInMap(elementCountMap);
    }

    private Map<T, Integer> getElementCountMap(T[] array) {
        Map<T, Integer> elementCountMap = new HashMap<>();
        for (T element : array) {
            elementCountMap.put(element, elementCountMap.getOrDefault(element, 0) + 1);
        }
        return elementCountMap;
    }

    private T getElementWithHighestValueInMap(Map<T, Integer> elementCountMap) {
        Integer mostCommonCount = Integer.MIN_VALUE;
        Integer currentCount;
        T mostCommon = null;
        for (T key: elementCountMap.keySet()) {
            currentCount = elementCountMap.get(key);
            if (currentCount > mostCommonCount) {
                mostCommon = key;
                mostCommonCount = currentCount;
            }
        }
        return mostCommon;
    }

    public Integer getNumberOfOccurrences(T valueToEvaluate) {
        int duplicateCount = 0;
        for (T element : array) {
            if (element.equals(valueToEvaluate)) duplicateCount++;
        }
        return duplicateCount;
    }

    @SuppressWarnings("unchecked")
    public T[] removeValue(T valueToRemove) {
        return Arrays.stream(array).filter(obj -> !obj.equals(valueToRemove)).toArray(size -> (T[]) Array.newInstance(array.getClass().getComponentType(), size));
    }
}
