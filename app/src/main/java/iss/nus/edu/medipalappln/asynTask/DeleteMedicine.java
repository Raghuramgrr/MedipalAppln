package iss.nus.edu.medipalappln.asynTask;

import android.content.Context;
import android.os.AsyncTask;

import iss.nus.edu.medipalappln.dao.ConsumptionDAO;
import iss.nus.edu.medipalappln.medipal.Consumption;
import iss.nus.edu.medipalappln.medipal.Medicine;
import iss.nus.edu.medipalappln.dao.MedicineDAO;

/**
 * Created by Darryl on 29/12/2016.
 */

public class DeleteMedicine extends AsyncTask<Medicine, Void, Integer> {
    Medicine medicine = null;
    private MedicineDAO medicineDAO;
    private ConsumptionDAO consumptionDAO;

    public DeleteMedicine(Context context) {
        this.medicineDAO = new MedicineDAO(context);
        this.consumptionDAO = new ConsumptionDAO(context);
    }

    @Override
    protected Integer doInBackground(Medicine... params) {
        int result = medicineDAO.delete(params[0]);
        int num = consumptionDAO.delete(params[0].getMedId());
        return result;
    }

    @Override
    protected void onPostExecute(Integer result) {
        if (result != -1)

            if (medicineDAO != null)
                medicineDAO.close();
    }
}
