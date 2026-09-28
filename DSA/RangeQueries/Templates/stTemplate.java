package Notebook.DSA.RangeQueries.Templates;

public class stTemplate {
        private class SegmentTree{
            int[] segmentTree;
            int[] inputArray;

            SegmentTree(int segmentTreeSize){
                this.segmentTree = new int[ 4 * segmentTreeSize];
            }

            private void dfsBuild(int node, int left, int right) {
                if (left == right) {
                    segmentTree[node] = inputArray[left];
                    return;
                }
            
                int mid = mid(left, right);
                dfsBuild(left(node), left, mid);
                dfsBuild(right(node), mid + 1, right);
            
                segmentTree[node] = segmentTree[left(node)] + segmentTree[right(node)];

                return;
            }       

            public void buildSegmentTree(int[] inputArray){
                this.inputArray = inputArray;
                dfsBuild(1, 0, inputArray.length - 1);
                return;
            }

            public void dfsUpdate(int nodeIndex, int index, int currLeft, int currRight, int value){
                if(currLeft > index || currRight < index)return;

                if(currLeft == currRight){
                    segmentTree[nodeIndex] = value;
                    inputArray[index] = value;
                    return;
                }

                int midRange = mid(currLeft, currRight);

                dfsUpdate(this.left(nodeIndex), index, currLeft, midRange, value);
                dfsUpdate(this.right(nodeIndex), index, midRange + 1, currRight, value);

                this.segmentTree[nodeIndex] = this.segmentTree[nodeIndex * 2] + this.segmentTree[nodeIndex * 2 + 1];
            }

            public void updateSegmentTree(int index, int value){
                int currLeft = 0;
                int currRight = this.inputArray.length - 1;
                dfsUpdate(1,index,currLeft,currRight,value);
                return;
            }

            public int dfsQuery(int nodeIndex, int leftRange, int rightRange, int currLeft, int currRight){
                if(leftRange <= currLeft && rightRange >= currRight) return segmentTree[nodeIndex];
                if(currLeft > rightRange)return 0;
                if(currRight < leftRange)return 0;
                if(currLeft == currRight)return segmentTree[nodeIndex];
                int midRange = mid(currLeft, currRight);

                int leftSubtree = 0, rightSubtree = 0;

                leftSubtree = dfsQuery(
                    this.left(nodeIndex),
                    leftRange,
                    rightRange,
                    currLeft,
                    midRange
                );

                rightSubtree = dfsQuery(
                    this.right(nodeIndex),
                    leftRange,
                    rightRange,
                    midRange + 1,
                    currRight
                );
                
                return leftSubtree + rightSubtree;

            }

            public int querySegmentTree(int leftRange, int rightRange){
                int currLeft = 0;
                int currRight = inputArray.length - 1;

                return dfsQuery(1, leftRange, rightRange, currLeft, currRight);
            }

            public int mid(int left, int right){
                return left + (right - left) / 2;
            }

            public int left(int currNodeIndex){
                return currNodeIndex * 2;
            }

            public int right(int currNodeIndex){
                return currNodeIndex * 2 + 1;
            }
        }
}
