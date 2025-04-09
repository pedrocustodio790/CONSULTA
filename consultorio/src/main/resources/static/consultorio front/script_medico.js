const API_BASE = 'http://localhost:8080';

document.addEventListener('DOMContentLoaded', () => {
  const form = document.getElementById('medico-form');
  const filtroBtn = document.getElementById('filtrar-medicos');

  form.addEventListener('submit', async event => {
    event.preventDefault();

    const medico = {
      nome: form.nome.value.trim(),
      crm: form.crm.value.trim(),
      especialidade: form.especialidade.value.trim(),
      telefone: form.telefone.value.trim(),
      email: form.email.value.trim(),
      nomeConsultorio: form.nomeConsultorio.value.trim(),
      numeroConsultorio: form.numeroConsultorio.value ? parseInt(form.numeroConsultorio.value) : null
    };

    try {
      const response = await fetch(`${API_BASE}/medicos`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(medico)
      });

      if (!response.ok) throw new Error('Erro ao cadastrar médico');

      alert('Médico cadastrado com sucesso!');
      form.reset();
      carregarMedicos();
    } catch (error) {
      alert(error.message);
    }
  });

  filtroBtn.addEventListener('click', carregarMedicos);
  carregarMedicos();
});

async function carregarMedicos() {
  try {
    const response = await fetch('http://localhost:8080/medicos');
    if (!response.ok) throw new Error('Erro ao obter médicos');

    const medicos = await response.json();
    const filtro = document.getElementById('filtroEspecialidade').value.toLowerCase();
    const tbody = document.querySelector('#medicos-table tbody');
    tbody.innerHTML = '';

    medicos
      .filter(m => m.especialidade.toLowerCase().includes(filtro))
      .forEach(medico => {
        const tr = document.createElement('tr');
        tr.appendChild(criarTd(medico.id));
        tr.appendChild(criarTd(medico.nome));
        tr.appendChild(criarTd(medico.crm));
        tr.appendChild(criarTd(medico.especialidade));
        tr.appendChild(criarTd(medico.telefone || ''));
        tr.appendChild(criarTd(medico.email || ''));
        tr.appendChild(criarTd(medico.nomeConsultorio || ''));
        tr.appendChild(criarTd(medico.numeroConsultorio || ''));
        tbody.appendChild(tr);
      });
  } catch (error) {
    alert(error.message);
  }
}

function criarTd(valor) {
  const td = document.createElement('td');
  td.textContent = valor;
  return td;
}
