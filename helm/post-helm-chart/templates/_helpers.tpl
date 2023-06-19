{{/*
Create current date
*/}}
{{- define "post-helm-chart.labels.date" -}}
{{- now | date "2006-01-02" }}
{{- end }}

{{/*
Create chart version as used by the chart label.
*/}}
{{- define "post-helm-chart.labels.version" -}}
{{- default .Chart.AppVersion }}
{{- end }}

