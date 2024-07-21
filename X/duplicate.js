function findDuplicateElements(arr) {
    const elementCount = new Map();
    const duplicates = [];
  
    for (let i = 0; i < arr.length; i++) {
      if (elementCount.has(arr[i])) {
        elementCount.set(arr[i], elementCount.get(arr[i]) + 1);
      } else {
        elementCount.set(arr[i], 1);
      }
    }
  
    elementCount.forEach((count, element) => {
      if (count > 1) {
        duplicates.push(element);
      }
    });
  
    return duplicates;
  }
  
  // 示例
  const inputArray = [1, 2, 3, 4, 2, 5, 6, 7, 8, 5, 9, 1];
  const result = findDuplicateElements(inputArray);
  console.log(result); // 输出: [1, 2, 5]
  