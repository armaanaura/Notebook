# Project Decision Log

## 1. Tokenization Strategy

**Doubt:** Should the model process Java code character by character or word by word?

**Decision:** Start with character-level tokenization. Move to subword tokenization (such as BPE) after the model and training pipeline work correctly.

**Reason:**

- Character tokenization is simple to implement and debug.
- It supports every identifier without unknown tokens.
- It preserves code formatting exactly.
- Whole-word tokenization cannot handle the unlimited variety of identifiers efficiently.
- Subword tokenization will later provide shorter sequences and a larger effective context.

**Progression:**

```text
Character tokenizer -> working model and training loop -> subword tokenizer
```
