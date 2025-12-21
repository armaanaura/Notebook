# Consistent Hashing
Consistent hashing is a technique used in distributed systems to decide which server stores which data, in a way that minimizes data movement if new servers are added or existing are removed.

Firstly lets understand what is hashing:

Hashing is a technique of getting a deterministic fixed size value for an input value. Also the output should be uniformly distributed over the target space.
- Fast Mapping `O(1)`
- Uniform Distribution
- Determinism (same input will always give same output)
- Low collison probability

## The Rehashing Problem

Let's say,

- I have 5 servers & DB
- I need to save my records in distributed mannar across all the servers
- Each record have a `unique_id`
- Easiest way I can do is, I can just run a loop and distribute the each query to one server and move to next for next query. Here is the code:

```java
final public int NUMBER_OF_SERVERS = 5;

public findServer(int uniqueId){
     return uniqueId % NUMBER_OF_SERVERS;
}
```
Now, the intput to output mapping will be like this:
| unique_id | Server |
|-----------|--------|  
| 1         | 1      |
| 2         | 2      |
| 5         | 5      |
| 6         | 1      |
| 7         | 2      |          
| ...       | ...    |
| 25        | 5      |

This approach works well until we server pool is fixed. But if I remove one server or add one server, the whole mapping will change and I will have to move all the data to new servers. This is called rehashing problem.

For example, if I remove server 5, the mapping will be like this:
| unique_id | 5 Servers | 4 Servers |     
|-----------|--------| -----------|
| 1         | 1      | 1      |
| 2         | 2      | 2      |
| 5         | 5      | 1      |
| 6         | 1      | 2      |
| 7         | 2      | 2      |

Now, when a request comes in for unique_id 5, it was earlier mapped to server 5, but now it is mapped to server 1. It may cause data missing. So, I have to move the data from server 5 to server 1. This will happen for all the records which were mapped to server 5. This is a big problem in distributed systems.

## Consistent Hashing to the Rescue

Consistent Hashing is a hashing technique such that when a server is added or removed, only `numberOfKeys/numberOfServers` number of keys need to be remapped.

So, basically if we have `x` number of keys in `nth` server, we need to remap only `nth` server keys when we add or remove a server.

### How Consistent Hashing Works?

Let's assume we are using [SHA-1](https://github.com/armaanaura/Notebook/tree/main/SystemDesign/Notes/SHA-1.md) hashing algorithm which gives us a 160 bit hash value, with range of `0` to `2^160 - 1`.

- Now for this range, we can represent it in a circular manner. This is called hash ring. ie, `0->1->2->3->...->2^160-1->0->1`

- Using this same hash function, we can hash the server names to get their position on the hash ring. For example, if we have 3 servers: `Server1`, `Server2`, `Server3`, we can hash them to get their positions on the hash ring.

- Now, when we want to store a record with `unique_id`, we can hash the `unique_id` to get its position on the hash ring. We then move clockwise on the ring until we find the first server. This server will be responsible for storing this record.

- For example, if we have 3 servers and their positions on the hash ring are:
  - Server1: 20
  - Server2: 70
  - Server3: 150

  And we want to store a record with `unique_id` which hashes to position 65. We will move clockwise on the ring and find that Server2 is the first server we encounter. So, Server2 will be responsible for storing this record.

- Now, if we add a new server, say `Server4`, we will hash it to get its position on the hash ring. Let's say it hashes to position 90. Now, when we want to store a record with `unique_id` which hashes to position 85, we will move clockwise on the ring and find that Server4 is the first server we encounter. So, Server4 will be responsible for storing this record.

- Catch here is, if we already have a record with `unique_id` which hashes to position 95, it was earlier stored in Server3. But now, it will be stored in Server4. So, we only need to move the records which are mapped to the new server. This is how consistent hashing minimizes data movement.

### Problems with currrent approach

- Non uniform distribution: If we have less number of servers, the distribution of keys may not be uniform. For example, if we have 3 servers and their positions on the hash ring are:
  - Server1: 10
  - Server2: 20
  - Server3: 150

  Now, if we want to store records with `unique_id` which hashes to positions between 21 to 149, they will all be stored in Server3. This will cause load imbalance.

- It is impossible to keep the same size of partitions on the ring for all the servers considering a server can be added or removed at any time.

### Virtual Nodes solve this problem

To solve the above problems, we can use the concept of virtual nodes.
- Instead of mapping a server to a single position on the hash ring, we can map it to multiple positions. These positions are called virtual nodes.
- For example, if we have 3 servers and we want to create 3 virtual nodes for each server, we can hash the server names with different suffixes to get their positions on the hash ring. For example:
  - Server1: 10, 50, 90
  - Server2: 20, 60, 100
  - Server3: 30, 70, 110

- Now, when we want to store a record with `unique_id`, we can hash it to get its position on the hash ring and move clockwise to find the first virtual node. The server corresponding to that virtual node will be responsible for storing the record.

### Redistribution of keys with add/removal of server
- When a new server is added, we create its virtual nodes and place them on the hash ring. Only the keys that map to the new virtual nodes need to be moved to the new server.
- When a server is removed, we remove its virtual nodes from the hash ring. The keys that mapped to those virtual nodes will be redistributed to the next virtual nodes on the ring.
- This way, consistent hashing with virtual nodes ensures a more uniform distribution of keys across servers and minimizes data movement when servers are added or removed.

### How to find the affected keys?
To find the affected keys when a server is added or removed, we can follow these steps:
1. When a server is added, hash its virtual nodes to get their positions on the hash ring.
2. For each virtual node of the new server, find the next virtual node on the hash ring (moving clockwise).
3. The keys that map to the range between the new virtual node and the next virtual node will be affected and need to be moved to the new server.
4. When a server is removed, hash its virtual nodes to get their positions on the hash ring.
5. For each virtual node of the removed server, find the next virtual node on the hash ring (moving clockwise).
6. The keys that map to the range between the removed virtual node and the next virtual node will be affected and need to be redistributed to the next virtual node's server.

### Example Code
```java
//assume we have a HashFunction class that provides SHA-1 hashing named sha1()
public 

```