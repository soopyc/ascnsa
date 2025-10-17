FROM python:3
COPY --from=docker.io/astral/uv:latest /uv /uvx /bin/

ADD . /app
WORKDIR /app

RUN --mount=type=cache,target=/root/.cache/uv \
	uv sync --locked --no-editable

EXPOSE 8000
CMD ["./.venv/bin/python", "-m", "gunicorn", "-w", "4", "library_app:app"]
