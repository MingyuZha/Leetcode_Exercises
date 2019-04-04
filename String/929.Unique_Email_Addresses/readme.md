# Leetcode 929. Unique Email Addresses

## Description

Every email consists of a local name and a domain name, separated by the @ sign.

For example, in `alice@leetcode.com`, `alice` is the local name, and `leetcode.com` is the domain name.

Besides lowercase letters, these emails may contain `'.'`s or `'+'`s.

If you add periods (`'.'`) between some characters in the **local name**part of an email address, mail sent there will be forwarded to the same address without dots in the local name.  For example, `"alice.z@leetcode.com"` and `"alicez@leetcode.com"` forward to the same email address.  (Note that this rule does not apply for domain names.)

If you add a plus (`'+'`) in the **local name**, everything after the first plus sign will be **ignored**. This allows certain emails to be filtered, for example `m.y+name@email.com` will be forwarded to `my@email.com`.  (Again, this rule does not apply for domain names.)

It is possible to use both of these rules at the same time.

Given a list of `emails`, we send one email to each address in the list.  How many different addresses actually receive mails? 

**Example 1:**

```
Input: ["test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"]
Output: 2
Explanation: "testemail@leetcode.com" and "testemail@lee.tcode.com" actually receive mails
```

## Solution 

这道题不存在什么难度，但是可以考察对java String标准库的掌握程度，本题中，可以用到的String API有：

```indexOf```, ```replace```, ```split```, ```substring```

```java
public int numUniqueEmails(String[] emails) {
  Set<String> seen = new HashSet(); //保存所有unique email addresses
  for (String email: emails) {
    int i = email.indexOf('@');  //找到分隔符所在index
    String local = email.substring(0, i); //@之前的为local name
    String rest = email.substring(i); //@之后的为domain name
    if (local.contains("+")) {
      local = local.substring(0, local.indexOf('+')); //如果local name中包含有‘+’符号，取'+'之前的字符串即可，这里也可以使用split方法: local = local.split("+")[0];
    }
    local = local.replace(".", ""); //将local name中所有的"."替换为""
    seen.add(local + rest);
  }

  return seen.size();
}
```

