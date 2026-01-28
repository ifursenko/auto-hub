export const useApi = () => {
  const config = useRuntimeConfig()
  const token = useState<string | null>('token', () => null)

  const apiFetch = async <T>(url: string, options: RequestInit = {}) => {
    const headers = new Headers(options.headers || {})
    if (token.value) {
      headers.set('Authorization', `Bearer ${token.value}`)
    }
    headers.set('Content-Type', 'application/json')
    const res = await fetch(`${config.public.apiBase}${url}`, {
      ...options,
      headers
    })
    if (!res.ok) {
      const text = await res.text()
      throw new Error(text || `Request failed: ${res.status}`)
    }
    return (await res.json()) as T
  }

  return { apiFetch, token }
}
