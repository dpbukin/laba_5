package com.example.lab_5.L6

import android.content.Context
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.lifecycleScope
import androidx.preference.EditTextPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import com.example.lab_5.R
import kotlinx.coroutines.launch
import java.io.File

class SettingsFragment : PreferenceFragmentCompat() {
    private val fileName = "heroes_20.txt"
    private lateinit var internalStorageFile: File

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
        internalStorageFile = File(requireContext().filesDir, fileName)
        checkFileStatus()

        val sharedPreferences = requireActivity().getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

        // Настройки для электронной почты
        val emailPreference = findPreference<EditTextPreference>("email")
        emailPreference?.setOnPreferenceChangeListener { _, newValue ->
            sharedPreferences.edit().putString("email", newValue as String).apply()
            true
        }

        // Тема оформления (DataStore)
        val themePreference = findPreference<SwitchPreference>("dark_theme")
        themePreference?.setOnPreferenceChangeListener { _, newValue ->
            lifecycleScope.launch {
                saveTheme(newValue as Boolean)
            }
            true
        }

        // Удаление файла
        findPreference<Preference>("delete_file")?.setOnPreferenceClickListener {
            deleteFile()
            true
        }

        // Восстановление файла
        findPreference<Preference>("restore_file")?.setOnPreferenceClickListener {
            restoreFile()
            true
        }
    }

    private fun checkFileStatus() {
        val externalFile = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), fileName)
        findPreference<Preference>("file_status")?.summary = if (externalFile.exists()) {
            "Файл существует во внешнем хранилище."
        } else {
            "Файл отсутствует во внешнем хранилище."
        }
    }

    private fun deleteFile() {
        val externalFile = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), fileName)
        if (externalFile.exists()) {
            backupFileToInternalStorage(externalFile)
            externalFile.delete()
            Toast.makeText(requireContext(), "Файл удалён из внешнего хранилища.", Toast.LENGTH_SHORT).show()
            checkFileStatus()
        } else {
            Toast.makeText(requireContext(), "Файл не найден.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun backupFileToInternalStorage(externalFile: File) {
        if (externalFile.exists()) {
            externalFile.copyTo(internalStorageFile, overwrite = true)
            Toast.makeText(requireContext(), "Файл сохранён во внутреннем хранилище.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun restoreFile() {
        if (internalStorageFile.exists()) {
            val externalFile = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), fileName)
            internalStorageFile.copyTo(externalFile, overwrite = true)
            Toast.makeText(requireContext(), "Файл восстановлен во внешнем хранилище.", Toast.LENGTH_SHORT).show()
            checkFileStatus()
        } else {
            Toast.makeText(requireContext(), "Резервная копия не найдена.", Toast.LENGTH_SHORT).show()
        }
    }

    private suspend fun saveTheme(isDarkMode: Boolean) {
        requireContext().dataStore.edit { settings ->
            settings[SettingsDataStore.PreferencesKeys.IS_DARK_MODE] = isDarkMode
        }
    }
}