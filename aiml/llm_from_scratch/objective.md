# Project Objective — Personal Java Code Completion Transformer

## 1. Project Goal

Build a small **decoder-only Transformer model from scratch using Python and PyTorch** that can autocomplete Java code.

The model should primarily learn:

1. **General Java coding syntax and structure**
2. **LeetCode-style competitive programming patterns**
3. **My personal Java coding style**

The long-term goal is not to build a general-purpose LLM. The goal is to build a focused code-completion model that can continue partially written Java code in a way that resembles how I normally write solutions.

---

## 2. Primary Use Case

The model will receive a partial Java code snippet such as:

```java
class Solution {
    public int maxSubArray(int[] nums) {
        int curr = nums[0];
        int ans = nums[0];

        for(int i = 1; i < nums.length; i++){
```

The model should generate a continuation such as:

```java
            curr = Math.max(nums[i], curr + nums[i]);
            ans = Math.max(ans, curr);
        }

        return ans;
    }
}
```

The model will work as an **autoregressive code-completion model**:

```text
previous code tokens
        ↓
predict next token
        ↓
append prediction
        ↓
predict next token
        ↓
repeat
```

---

## 3. Final Objective

A model-generated completion will be considered successful when it satisfies the following requirements.

### Primary Success Criterion

The generated Java code should:

- be syntactically valid Java
- have balanced braces and parentheses
- contain valid method/class structure
- compile successfully using `javac`

Target:

```text
Compilation success rate: >= 95%
```

on a held-out test set of Java code completions.

### Secondary Success Criterion

The model should gradually learn my coding style, including patterns such as:

- variable naming
- helper function structure
- recursion style
- DP array naming
- loop formatting
- brace placement
- common solution structure
- preference for certain Java constructs

### Later Success Criterion

After compilation reliability is achieved:

```text
partial code
    ↓
model completion
    ↓
compile
    ↓
run test cases
    ↓
measure correctness
```

Correctness on hidden test cases will become the next major evaluation metric.

---

## 4. Scope

### Included in Version 1

- Java only
- LeetCode-style code
- next-token prediction
- code completion
- decoder-only Transformer
- causal self-attention
- custom Transformer implementation
- training on Apple Silicon
- evaluation using Java compilation

### Not Included Initially

- natural-language problem statements
- explanation generation
- multi-language programming support
- chat interface
- retrieval systems
- very large models
- full reproduction of GPT
- production-scale inference

---

## 5. Architecture

The model will be based on the core ideas from **Attention Is All You Need**, but adapted into a decoder-only architecture suitable for code completion.

```text
Java source code
      ↓
Tokenizer
      ↓
Token embeddings
      ↓
Positional information
      ↓
┌─────────────────────────┐
│ Masked Self-Attention   │
│ Residual + LayerNorm    │
│ Feed-Forward Network    │
│ Residual + LayerNorm    │
└─────────────────────────┘
      ↓
repeat N times
      ↓
Linear projection
      ↓
Softmax
      ↓
Probability of next token
```

The initial implementation should avoid high-level Transformer APIs such as:

```python
torch.nn.Transformer
torch.nn.MultiheadAttention
```

Core components should be implemented manually using PyTorch tensor operations so that the architecture is properly understood.

---

## 6. Core Components to Implement

The project should be built in this order:

1. Tokenizer
2. Token embeddings
3. Positional encoding / positional embeddings
4. Query, Key and Value projections
5. Scaled dot-product attention
6. Causal masking
7. Multi-head self-attention
8. Feed-forward network
9. Residual connections
10. Layer normalization
11. Transformer decoder block
12. Full decoder-only Transformer
13. Language-model output head
14. Training loop
15. Text/code generation loop
16. Java compilation evaluator

---

## 7. Dataset Strategy

Training only on my own LeetCode submissions will probably not provide enough data for the model to learn Java syntax reliably.

Therefore training will happen in two stages.

### Stage 1 — General Java Training

Use a clean external dataset containing Java source code.

Purpose:

```text
learn Java syntax
learn common programming structures
learn braces and indentation patterns
learn methods/classes
learn loops and conditions
learn common algorithms
```

The dataset should remain reasonably small so training is practical on a MacBook Air.

Initial target:

```text
few MB → tens of MB of Java code
```

### Stage 2 — Personal Style Adaptation

Train further on my own accepted LeetCode Java submissions.

Purpose:

```text
general Java model
        ↓
my LeetCode submissions
        ↓
model adapted toward my coding style
```

My submissions should be treated primarily as the **style-specialization dataset**, rather than the entire pretraining dataset.

---

## 8. Training Objective

The model will use standard autoregressive next-token prediction.

For a sequence:

```text
public static int max
```

the training relationship is conceptually:

```text
public              → static
public static       → int
public static int   → max
```

During training, predictions for all positions will be calculated in parallel using a causal attention mask.

The model must never see future tokens while predicting the current token.

---

## 9. Initial Model Size

The first model should be small enough to train and experiment with locally.

Suggested starting configuration:

```text
Transformer layers: 4
Attention heads:    4
d_model:            256
Context length:     256 tokens
Feed-forward size:  ~1024
```

Target parameter range:

```text
~5M–20M parameters
```

The architecture can be scaled later after the training pipeline works correctly.

---

## 10. Hardware Constraint

Primary development/training machine:

```text
Apple MacBook Air
Apple M5
16 GB unified memory
```

Training should use:

```python
device = "mps"
```

when Apple Metal acceleration is available.

The model should be intentionally small because:

- unified memory is limited
- the MacBook Air is fanless
- long training sessions can cause thermal throttling
- fast experimentation is more important than model size

---

## 11. Evaluation

### Metric 1 — Validation Loss

Track whether next-token prediction improves during training.

```text
training loss ↓
validation loss ↓
```

This confirms that the model is learning the dataset.

### Metric 2 — Java Appearance

Early generations should gradually progress from:

```text
random characters
```

to:

```text
Java-like structures
```

then:

```text
valid methods/classes
```

### Metric 3 — Compilation Rate

Create a held-out test set.

For each example:

```text
original Java code
      ↓
keep first 30–70%
      ↓
ask model to generate remainder
      ↓
combine code
      ↓
javac
```

Measure:

```text
successful compilations
------------------------
total completions
```

Primary target:

```text
>= 95%
```

### Metric 4 — Style Similarity

Compare generated code against my coding habits.

Possible future measurements:

- variable-name similarity
- indentation consistency
- helper-method patterns
- DP-array naming
- loop construction
- structural similarity

### Metric 5 — Functional Correctness

Later:

```text
compile generated solution
        ↓
execute against test cases
        ↓
measure percentage passing
```

---

## 12. Development Milestones

### Milestone 1 — Understand Transformer Fundamentals

Understand and manually implement:

- embeddings
- Q, K, V
- attention scores
- softmax
- scaled dot-product attention
- causal masking

Success condition:

```text
attention function works correctly on small tensors
```

---

### Milestone 2 — Build One Transformer Block

Implement:

- multi-head attention
- LayerNorm
- residual connections
- feed-forward network

Success condition:

```text
input tensor
    ↓
Transformer block
    ↓
same expected output dimensions
```

---

### Milestone 3 — Build Decoder-Only Transformer

Stack multiple Transformer blocks.

Add:

- vocabulary projection
- next-token probabilities
- causal mask

Success condition:

```text
code tokens → probability distribution over next token
```

---

### Milestone 4 — Overfit a Tiny Dataset

Train on a very small Java dataset.

Purpose:

Verify that:

- forward pass works
- backward pass works
- optimizer works
- loss decreases
- generation works

The model should intentionally overfit this dataset.

---

### Milestone 5 — General Java Training

Train on a larger Java corpus.

Expected progression:

```text
random text
→ Java-looking text
→ valid Java fragments
→ increasingly valid code
```

---

### Milestone 6 — Personal Style Training

Fine-tune on my accepted LeetCode submissions.

Goal:

```text
generic Java completion
        ↓
Java completion resembling my coding style
```

---

### Milestone 7 — Compilation Evaluation

Create an automatic evaluation system:

```text
prompt
↓
model completion
↓
write .java file
↓
javac
↓
record success/failure
```

Target:

```text
>= 95% compilation rate
```

---

### Milestone 8 — Correctness Evaluation

After compilation quality becomes reliable:

```text
generated code
↓
compile
↓
execute
↓
LeetCode-style test cases
↓
pass/fail
```

---

## 13. Suggested Project Structure

```text
java-code-transformer/
│
├── data/
│   ├── raw/
│   ├── processed/
│   └── personal/
│
├── tokenizer/
│   └── tokenizer.py
│
├── model/
│   ├── attention.py
│   ├── embeddings.py
│   ├── feed_forward.py
│   ├── block.py
│   └── transformer.py
│
├── training/
│   ├── dataset.py
│   ├── train.py
│   └── config.py
│
├── inference/
│   └── generate.py
│
├── evaluation/
│   ├── compile_test.py
│   └── style_eval.py
│
├── checkpoints/
│
├── experiments/
│
├── objective.md
└── README.md
```

---

## 14. Learning Objective

The project is also intended to build a deep understanding of Transformer and LLM internals.

By completing it, I should understand:

- tokenization
- embeddings
- positional information
- Q/K/V
- scaled dot-product attention
- self-attention
- causal attention
- multi-head attention
- residual connections
- LayerNorm
- feed-forward layers
- autoregressive language modeling
- cross-entropy loss
- gradient-based training
- model generation
- sampling
- context windows
- training/validation splits
- overfitting
- fine-tuning
- evaluation of generative models

The emphasis is on **implementing and understanding the architecture**, not simply using an existing Transformer implementation.

---

## 15. Definition of Done — Version 1

Version 1 of the project is complete when:

- [ ] Transformer components are implemented manually
- [ ] decoder-only model trains successfully
- [ ] training runs using Apple MPS
- [ ] model can generate Java code continuations
- [ ] model has been trained on general Java code
- [ ] model has been adapted using my LeetCode submissions
- [ ] automatic `javac` evaluation exists
- [ ] held-out completion dataset exists
- [ ] generated code achieves at least 95% compilation success

Functional correctness and advanced style evaluation will belong to later versions.

---

## 16. Long-Term Direction

After Version 1:

```text
Java syntax
    ↓
reliable compilation
    ↓
personal coding style
    ↓
algorithmic correctness
    ↓
problem-statement → solution generation
```

Eventually, the project can evolve from a small code-completion model into a focused personal coding assistant trained around my own programming patterns.
