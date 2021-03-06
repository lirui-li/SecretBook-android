package edu.upc.mishu.services;

import android.app.assist.AssistStructure;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.CancellationSignal;
import android.service.autofill.AutofillService;
import android.service.autofill.Dataset;
import android.service.autofill.FillCallback;
import android.service.autofill.FillContext;
import android.service.autofill.FillRequest;
import android.service.autofill.FillResponse;
import android.service.autofill.SaveCallback;
import android.service.autofill.SaveInfo;
import android.service.autofill.SaveRequest;
import android.service.autofill.Transformation;
import android.util.ArrayMap;
import android.util.Log;
import android.view.autofill.AutofillId;
import android.view.autofill.AutofillValue;
import android.widget.RemoteViews;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import edu.upc.mishu.App;
import edu.upc.mishu.R;
import edu.upc.mishu.dto.PasswordRecord;
import edu.upc.mishu.interfaces.Transformable;
import edu.upc.mishu.model.AES256Enocder;
import lombok.SneakyThrows;

@RequiresApi(api = Build.VERSION_CODES.O)
public class AutofillServiceTest extends AutofillService {
    private List<PasswordRecord> data;
    private static final String TAG = "AutofillServiceTest";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: ");
    }

    @Override
    public void onDisconnected() {
        super.onDisconnected();
        Log.i(TAG, "onDisconnected: ");
    }

    @Override
    public void onConnected() {
        super.onConnected();
        Log.i(TAG, "onConnected: ");
        data = PasswordRecord.listAll(PasswordRecord.class);
    }

    @SneakyThrows
    @Override
    public void onFillRequest(@NonNull FillRequest request, @NonNull CancellationSignal cancellationSignal, @NonNull FillCallback callback) {
        List<PasswordRecord> autodata = new ArrayList<>();
        List<FillContext> fillContextList = request.getFillContexts();
        AssistStructure structure = fillContextList.get(fillContextList.size() - 1).getStructure();
        Log.i(TAG, "onFillRequest: app name :" + getAppName(structure));
        Map<String, AutofillId> fields = traverseStructure(structure);

        if (fields.isEmpty()) {
            Toast.makeText(getApplicationContext(), "No autofill hints found", Toast.LENGTH_SHORT).show();
            callback.onSuccess(null);
            return;
        }

        Log.i(TAG, "onFillRequest: " + getAppName(structure));
        for(PasswordRecord item:data){
            item.decode(App.encoder,1);
            if (item.getName().equals(getAppName(structure))){
                autodata.add(item);
            }
        }

        Log.i(TAG, "onFillRequest: autodata "+autodata.toString());
        FillResponse.Builder response = new FillResponse.Builder();

        // 动态添加数据
        if(autodata.size()!=0){
            Log.i(TAG, "onFillRequest: add data");
            String packgeName = getApplicationContext().getPackageName();
            for (int i = 0; i < autodata.size(); i++) {
                Dataset.Builder dataset = new Dataset.Builder();
                for (Map.Entry<String, AutofillId> field : fields.entrySet()) {
                    String hint = field.getKey();
                    AutofillId id = field.getValue();
                    RemoteViews remoteViews = new RemoteViews(packgeName,android.R.layout.simple_list_item_1);
                    if(hint.contains("密码")){
                        remoteViews.setTextViewText(android.R.id.text1,autodata.get(i).getPassword());
                        dataset.setValue(id,AutofillValue.forText(autodata.get(i).getPassword()),remoteViews);
                    }else{
                        remoteViews.setTextViewText(android.R.id.text1,autodata.get(i).getUsername());
                        dataset.setValue(id,AutofillValue.forText(autodata.get(i).getUsername()),remoteViews);
                    }
                }
                response.addDataset(dataset.build());
            }
        }

        //保存信息
        Collection<AutofillId> ids = fields.values();
        AutofillId[] requiredIds = new AutofillId[ids.size()];
        ids.toArray(requiredIds);
        response.setSaveInfo(new SaveInfo.Builder(SaveInfo.SAVE_DATA_TYPE_GENERIC, requiredIds).build());

        callback.onSuccess(response.build());

    }

    @SneakyThrows
    @Override
    public void onSaveRequest(@NonNull SaveRequest request, @NonNull SaveCallback callback) {
        Log.i(TAG, "onSaveRequest: ");
        List<FillContext> fillContextList = request.getFillContexts();
        AssistStructure structure = fillContextList.get(fillContextList.size() - 1).getStructure();
        List<String> stringList = getinfo(structure);
        PasswordRecord.builder().type("Android").name(stringList.get(0)).url("null").username(stringList.get(1)).password(stringList.get(2)).note(" ").build().encode(App.encoder, 1).save();
        callback.onSuccess();

    }

    public AutofillServiceTest() {
        super();
    }

    public Map<String, AutofillId> traverseStructure(AssistStructure structure) {
        Map<String, AutofillId> fields = new ArrayMap<>();
        int nodes = structure.getWindowNodeCount();

        for (int i = 0; i < nodes; i++) {
            AssistStructure.WindowNode windowNode = structure.getWindowNodeAt(i);
            AssistStructure.ViewNode viewNode = windowNode.getRootViewNode();
            traverseNode(viewNode, fields);
        }
        return fields;
    }

    public void traverseNode(AssistStructure.ViewNode viewNode, Map<String, AutofillId> fields) {

//        String[] hints = viewNode.getAutofillHints();
//        Log.i(TAG, "traverseNode: "+hints);
//
//        if(hints !=null){
//            String hint = hints[0].toLowerCase();
//            if (hint != null){
//                AutofillId id = viewNode.getAutofillId();
//                if (!fields.containsKey(hint)){
//                    Log.i(TAG, "traverseNode: setting hint "+hint+" on "+id);
//                    fields.put(hint,id);
//                }else{
//                    Log.i(TAG, "traverseNode: already set " +"hint is "+hint + "on "+id);
//                }
//            }
//        }

        String hint = viewNode.getHint();
        if (hint != null) {
            AutofillId id = viewNode.getAutofillId();
            if (!fields.containsKey(hint)) {
                //Log.i(TAG, "traverseNode: setting hint" + hint + "on " + id);
                fields.put(hint, id);
            } else {
                //Log.i(TAG, "traverseNode: hint already set");
            }
        }

        for (int i = 0; i < viewNode.getChildCount(); i++) {
            AssistStructure.ViewNode childNode = viewNode.getChildAt(i);
            // Log.i(TAG, "traverseNode: " + childNode.getClassName() + " " + childNode.getIdPackage() + " " + childNode.getText() + " " + childNode.getHint() + " " + childNode.getAutofillId());
            traverseNode(childNode, fields);
        }
    }
    public List<String> getinfo(AssistStructure structure) throws PackageManager.NameNotFoundException {
        List<String> fields = new ArrayList<>();
        int nodes = structure.getWindowNodeCount();
        String name = getAppName(structure);
        //Log.i(TAG, "getinfo: "+structure.getActivityComponent().getPackageName());
        fields.add(name);
        for (int i = 0; i < nodes; i++) {
            AssistStructure.WindowNode windowNode = structure.getWindowNodeAt(i);
            AssistStructure.ViewNode viewNode = windowNode.getRootViewNode();
            getinfoNode(viewNode, fields);
        }
        return fields;
    }

    public void getinfoNode(AssistStructure.ViewNode viewNode, List<String> fields) {
        String hint = viewNode.getHint();
        if (hint != null) {
            String value = viewNode.getText().toString();
            if (!fields.contains(hint)) {
                //Log.i(TAG, "traverseNode: setting hint" + hint + "value" + value);
                fields.add(value);
            } else {
                //Log.i(TAG, "traverseNode: hint already set");
            }
        }
        for (int i = 0; i < viewNode.getChildCount(); i++) {
            AssistStructure.ViewNode childNode = viewNode.getChildAt(i);
            //Log.i(TAG, "traverseNode: " + childNode.getClassName() + " " + childNode.getIdPackage() + " " + childNode.getText() + " " + childNode.getHint() + " " + childNode.getAutofillId());
            getinfoNode(childNode, fields);
        }
    }




    public String getAppName(AssistStructure structure) throws PackageManager.NameNotFoundException {
        return getBaseContext().getPackageManager().getApplicationLabel(getBaseContext().getPackageManager().getApplicationInfo(structure.getActivityComponent().getPackageName(), PackageManager.GET_META_DATA)).toString();
    }

}
