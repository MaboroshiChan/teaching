let str = "abcdefgfnsefikdsk";
let str2 = "dnwdkiwddabcd";
let fn = function (s1, s2) {
    let lcs = Array(s1.length + 1)
        .fill()
        .map(() => Array(s2.length + 1).fill(0));
    let maxlength = 0;
    let endindex = 0;
    for (let i = 1; i <= s1.length; i++) {
        for (let j = 1; j <= s2.length; j++) {
            if (s1[i - 1] === s2[j - 1]) {
                lcs[i][j] = lcs[i - 1][j - 1] + 1;
                if (lcs[i][j] > maxlength) {
                    maxlength = lcs[i][j];
                    endindex = i - 1;
                    console.log(maxlength, endindex)
                }
            } else {
                lcs[i][j] = 0;
            }
        }
    }
    return s1.slice(endindex - maxlength + 1, endindex + 1);
};
function longestCommonSubstring(s1, s2) {
    // 初始化一个二维数组用于存储每个子串的长度
    const lcs = Array(s1.length + 1).fill().map(() => Array(s2.length + 1).fill(0));
    let maxLength = 0;
    let endIndex = 0;
    
    // 填充数组，记录每个子串的长度
    for (let i = 1; i <= s1.length; i++) {
      for (let j = 1; j <= s2.length; j++) {
        if (s1[i - 1] === s2[j - 1]) {
          lcs[i][j] = lcs[i - 1][j - 1] + 1;
          if (lcs[i][j] > maxLength) {
            maxLength = lcs[i][j];
            endIndex = i - 1;
          }
        } else {
          lcs[i][j] = 0;
        }
      }
    }
    
    // 根据结束索引和最大长度，计算出最长公共子串并返回
    if (maxLength === 0) {
      return "";
    } else {
      return s1.slice(endIndex - maxLength + 1, endIndex + 1);
    }
  }
  
console.log(fn(str, str2));
console.log(longestCommonSubstring(str, str2))