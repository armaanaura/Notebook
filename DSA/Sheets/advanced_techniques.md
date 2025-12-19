# Advanced Techniques to Learn (Not required for MAANG)


| Area              | Technique / Trick             | What it’s used for                            | Key idea (short)                              | Typical difficulty/where seen | Practice sources / tags |
|-------------------|-------------------------------|-----------------------------------------------|-----------------------------------------------|-------------------------------|-------------------------|
| Bitmask / Subsets | SOS DP                        | Fast transform over subsets/supersets.        | Accumulate DP over submasks by iterating bits | CF 2000+ / ICPC               | dp, bitmasks            |
| Bitwise           | Gosper’s Hack                 | Iterate next mask with same popcount.         | Next-combination bit trick                    | ICPC / CF niche               | bitmasks                |
| Bitwise           | Bitset DP optimization        | Speed knapsack/subset-sum.                    | dp |= dp<<w using bitsets                     | CF / ICPC                     | bitset, dp              |
| Bitwise           | Meet-in-the-Middle            | Subset sum / search for n≈40.                 | Split, enumerate halves, combine              | CF / ICPC                     | mitm                    |
| Math              | Mobius inversion              | Coprime counts / multiplicative transforms.   | Invert divisor-sum relations via μ(n)         | CF 2200+ / ICPC               | number theory           |
| Math              | CRT                           | Combine congruences.                          | Solve x≡a(mod m), x≡b(mod n)                  | ICPC / CF rare                | number theory           |
| Math              | NTT                           | Polynomial multiplication mod prime.          | FFT variant in modular arithmetic             | CF Div1 / ICPC                | fft/ntt                 |
| Math              | FWHT                          | XOR/AND/OR convolutions.                      | Transform–multiply–inverse                    | CF 2200+                      | bitwise convolution     |
| Math              | Burnside/Polya                | Count colorings up to symmetry.               | Average fixed points over group actions       | ICPC / niche                  | combinatorics           |
| Geometry          | Rotating calipers             | Farthest pair / widths on convex hull.        | Two pointers on hull                          | ICPC / geometry               | geometry                |
| Geometry          | Half-plane intersection       | Intersect many half-planes.                   | Maintain deque of lines by angle              | ICPC advanced                 | geometry                |
| Geometry          | Pick’s theorem                | Area from lattice points.                     | A = I + B/2 − 1                               | CF niche                      | geometry, math          |
| DP Optimization   | Knuth optimization            | Speed interval DP with quadrangle inequality. | opt[l][r] monotonic                           | ICPC / niche                  | dp optimization         |
| DP Optimization   | Divide & Conquer DP           | Optimize k-split transitions.                 | Compute dp row with D&C over opt range        | CF / ICPC                     | dp optimization         |
| DP Optimization   | Convex Hull Trick             | Optimize DP with linear functions.            | Maintain hull of lines, query min/max         | CF 2100+ / ICPC               | cht                     |
| DP Optimization   | Aliens trick                  | Penalty trick to satisfy constraint count.    | Add λ, solve, adjust via binary search        | CF niche                      | parametric search       |
| DP Optimization   | Monotonic queue DP            | Optimize DP with sliding min/max.             | Deque of candidate states                     | CF / some LC hards            | dp, deque               |
| DP Optimization   | SMAWK                         | Find row minima in totally monotone matrices. | Matrix search faster than O(nm)               | ICPC / very niche             | dp optimization         |
| Data Structures   | Persistent segment tree       | Versioned queries (kth in subarray).          | Immutable versions sharing nodes              | CF 2200+                      | persistent, segtree     |
| Data Structures   | DSU on Tree (small-to-large)  | Subtree query aggregation.                    | Merge small map into big                      | CF 2000+                      | trees                   |
| Data Structures   | Mo’s algorithm                | Offline range queries.                        | Reorder by blocks; add/remove                 | CF / ICPC                     | offline                 |
| Data Structures   | Li Chao Tree                  | Dynamic CHT queries.                          | Segment-tree-like hull for lines              | CF advanced                   | cht                     |
| Graphs            | Tarjan bridges/articulation   | Critical edges/vertices.                      | Low-link values                               | CF / ICPC                     | graphs                  |
| Graphs            | SCC (Kosaraju/Tarjan)         | Condense directed graph.                      | Component DAG                                 | CF / ICPC                     | graphs                  |
| Graphs            | Dinic / Push-relabel max flow | High-performance max flow.                    | Level graph + blocking flow                   | ICPC / CF                     | flow                    |
| Graphs            | Min-cost max-flow             | Flow with costs (assignment).                 | Shortest augmenting paths with potentials     | ICPC / CF                     | flow                    |
| Graphs            | Centroid decomposition        | Distance/path queries on trees.               | Decompose by centroid; store dist info        | CF 2200+                      | trees                   |
| Graphs            | HLD                           | Path queries on trees.                        | Decompose to heavy paths + segtree            | CF / ICPC                     | trees, segtree          |
