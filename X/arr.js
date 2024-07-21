function findIndex(arr) {
  let leftSum = 0;
  let rightSum = arr.reduce((a, b) => a + b, 0);

  for (let i = 0; i < arr.length; i++) {
    rightSum -= arr[i];

    if (leftSum === rightSum) {
      return i;
    }

    leftSum += arr[i];
  }

  return -1;
}

// 示例用法
const arr = [1, 2, 3, 4, 3, 2, 1];
const index = findIndex(arr);

if (index === -1) {
  console.log("找不到符合条件的下标");
} else {
  console.log(`下标为 ${index}`);
}