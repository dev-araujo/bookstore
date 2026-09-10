---
name: readme-template
description: Generate a project README from the canonical example.md template, adapted to the current project.
type: project
---

**Rule:** When the user asks to create or regenerate a README, use `example.md` as the
canonical template — fill every placeholder with the actual project's info (stack, run
commands, repo URL, author, badges).

**Why:** Keeps every project's README consistent, professional, and complete without
re-inventing the wheel each time.

**How to apply:**
1. Read `example.md` — it is a generic template with placeholders.
2. Read `pom.xml` (or equivalent build file) for stack versions.
3. Read `.env.example` for env keys.
4. Read `src/` structure for architecture notes.
5. Produce `README.md` with:
   - Shields badges for each technology (use correct logo/color slugs).
   - Project name & one-line description.
   - Stack section split by Backend / Frontend / External Services.
   - Environment Configuration section with real `.env` keys.
   - Running Locally section with both manual and Docker sub-sections.
   - Architecture & Conventions section drawn from CLAUDE.md or project norms.
   - Author section with the user's GitHub avatar and LinkedIn.
6. Do NOT overwrite `example.md` — it is the source template.
