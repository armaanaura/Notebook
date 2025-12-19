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